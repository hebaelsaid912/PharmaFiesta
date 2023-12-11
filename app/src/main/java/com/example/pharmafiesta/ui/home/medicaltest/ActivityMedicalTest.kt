package com.example.pharmafiesta.ui.home.medicaltest

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.pharmafiesta.databinding.ActivityMedicalTestBinding
import com.example.pharmafiesta.ui.home.BottomNavigationActivity
import com.example.pharmafiesta.ui.home.medicaltest.adapter.AdapterMedicalTest
import com.example.pharmafiesta.ui.home.medicaltest.model.MedicalTestModel
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText

@Suppress("DEPRECATION")
class ActivityMedicalTest : AppCompatActivity() {

    lateinit var binding: ActivityMedicalTestBinding

    private val REQUEST_IMAGE_CAPTURE = 1

    private var tests = ArrayList<MedicalTestModel>()

    private val adapterMedicalTest by lazy { AdapterMedicalTest() }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicalTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()


        binding.imgBack.setOnClickListener {
            val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.captureButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_IMAGE_CAPTURE
                )
            } else {
                dispatchTakePictureIntent()
            }

        }

    }

    private fun initView() {
        binding.rvTests.adapter = adapterMedicalTest

    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    binding.progress.isVisible = true
                    // Check if the image is from the camera
                    if (data != null && data.extras != null && data.extras!!.containsKey("data")) {
                        // Image captured from camera
                        val imageBitmap = data.extras!!.get("data") as Bitmap?
                        imageBitmap?.let { processImage(it) }
                    }
                }
            }
        }
    }


    private fun processImage(bitmap: Bitmap) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val textRecognizer = FirebaseVision.getInstance()
            .onDeviceTextRecognizer
        textRecognizer.processImage(image)
            .addOnSuccessListener { firebaseVisionText -> // Handle text recognition results
                handleTextRecognitionResults(firebaseVisionText)
            }
            .addOnFailureListener { e -> // Handle failures
                e.printStackTrace()
            }
    }

    private fun handleTextRecognitionResults(firebaseVisionText: FirebaseVisionText) {
        // Process the recognized text
        for (block in firebaseVisionText.textBlocks) {
            val blockText: String = block.text
            analyzeBloodPictureContent(blockText)
            // Process each block of text
        }
//        val recognizedText = firebaseVisionText.text
//        // Analyze the recognized text for sugar content
//        analyzeBloodPictureContent(recognizedText)

    }

    private fun analyzeBloodPictureContent(text: String) {

        val lines = text.split("\n")

        val text1 = lines.toString().replace("[", "")
        val text2 = text1.replace("]", "")

        // Split the input string into parts
        val parts = text2.split(",").map { it.trim() }

        // Lists to store strings and numbers
        val stringList = mutableListOf<String>()
        val numberList = mutableListOf<Double>()

        // Iterate through the parts
        for (part in parts) {
            // Check if the part is a number
            val number = part.toDoubleOrNull()

            if (number != null) {
                // It's a number, add it to the number list
                numberList.add(number)
            } else {
                // It's a string, add it to the string list
                stringList.add(part)
            }
        }


        stringList.forEachIndexed { index, test ->
            if (test.contains("Hemoglobin") || test.contains("Homoglobin") || test.contains("Hgb")) {
                if (numberList[index] in 12.6..17.4) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(Hemoglobin Hgb)",
                            percentage = "${numberList[index]}",
                            testRange = "12.6 : 17.4",
                            testStatus = "  مستوى هيموجلوبين في دم المريض في النطاق الصحي ولا يشير إلى وجود أي اضطراب كبير في مستوى الهيموجلوبين"
                        )
                    )
                } else if (numberList[index] > 17.4) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(Hemoglobin Hgb)",
                            percentage = "${numberList[index]}",
                            testRange = "12.6 : 17.4",
                            testStatus = "   مستوي الهيموجلوبين لديك اعلي من المعدل الطبيعي الذي يتراوح بين (12.6 - 17.4) حيث الارتفاع في مستوى الهيموجلوبين قد يكون نتيجة لعدة أسباب، ويمكن أن يكون هناك تأثيرات مختلفة اعتمادًا على الحالة الصحية العامة للفرد وظروفه لذلك افضل زياره الطبيب لمعرفه سياق حالتك"
                        )
                    )
                } else {
                    tests.add(
                        MedicalTestModel(
                            testName = "(Hemoglobin Hgb)",
                            percentage = "${numberList[index]}",
                            testRange = "12.6 : 17.4",
                            testStatus = "  مستوي الهيموجلوبين لديك اقل من المعدل الطبيعي الذي يتراوح بين (12.6 - 17.4) حيث الانخفاض في مستوى الهيموجلوبين  قد يكون مؤشرًا على حالة منخفضة في نسبة الدم الحمراء أو قد يكون ناتجًا عن فقر الدم. يمكن أن يسبب انخفاض مستوى الهيموجلوبين أعراضًا مثل الإعياء، فقدان الشهية، ضيق التنفس، والتعب لذلك افضل زياره الطبيب لمعرفه سياق حالتك"
                        )
                    )
                }
            }

            if (test.contains("RBCS") || test.contains("RBCs")) {
                if (numberList[index] in 3.8..6.0) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(RBCs)",
                            percentage = "${numberList[index]}",
                            testRange = "3.8 : 6.0",
                            testStatus = " نسبة الكريات الحمراء في الدم  التي تتراوح بين 3.8 و6.0 تشير إلى نطاق طبيعي لعدد الكريات الحمراء في الدم، وهي تُقاس بمليون الكرية الحمراء في الميكروليتر الواحد من الدم"
                        )
                    )
                } else if (numberList[index] > 6.0) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(RBCs)",
                            percentage = "${numberList[index]}",
                            testRange = "3.8 : 6.0",
                            testStatus = "مستوي الكريات الحمراء في الدم لديك اعلي من المعدل الطبيعي الذي يتراوح بين (3.8 - 6.0) حيث الارتفاع في نسبة الكريات الحمراء في الدم قد يكون نتيجة لعدة أسباب، ويمكن أن يكون هناك تأثيرات مختلفة اعتمادًا على الحالة الصحية العامة للفرد وظروفه لذلك افضل زياره الطبيب لمعرفه سياق حالتك"
                        )
                    )
                } else {
                    tests.add(
                        MedicalTestModel(
                            testName = "(RBCs)",
                            percentage = "${numberList[index]}",
                            testRange = "3.8 : 6.0",
                            testStatus = "مستوي الكريات الحمراء في الدم لديك اقل من المعدل الطبيعي الذي يتراوح بين (3.8 - 6.0) حيث الانخفاض في مستوى  الكريات الحمراء في الدم  قد يكون مؤشرًا على حالة منخفضة في نسبة الدم الحمراء أو قد يكون ناتجًا عن فقر الدم. يمكن أن يسبب انخفاض مستوى الكريات الحمراء الي امراض خطيره لذلك يجب استشاره الطبيب فورا "
                        )
                    )
                }
            }


            if (test.contains("Hct") || test.contains("Hematocrit")) {
                if (numberList[index] in 37.0..54.0) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(Hct)",
                            percentage = "${numberList[index]}",
                            testRange = "37.0 : 54.0",
                            testStatus = " نسبة هيماتوكريت الطبيعية  التي تتراوح بين 37 و54 تشير إلى نطاق طبيعي لهيماتوكريت"
                        )
                    )
                } else if (numberList[index] > 54.0) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(Hct)",
                            percentage = "${numberList[index]}",
                            testRange = "37.0 : 54.0",
                            testStatus = "نسبة هيماتوكريت اعلي من المعدل الطبيعي الذي يتراوح بين (37 - 54) حيث الارتفاع يمكن أن يشير إلى ارتفاع في نسبة حجم كريات الدم الحمراء مقارنة بحجم الدم الكلي لذلك افضل زياره الطبيب لمعرفه سياق حالتك"
                        )
                    )
                } else {
                    tests.add(
                        MedicalTestModel(
                            testName = "(Hct)",
                            percentage = "${numberList[index]}",
                            testRange = "37.0 : 54.0",
                            testStatus = " نسبة هيماتوكريت اقل من المعدل الطبيعي الذي يتراوح بين (37 - 54) حيث الانخفاض في نسبة حجم كريات الدم الحمراء مقارنة بحجم الدم الكلي أو قد يكون ناتجًا عن فقر الدم. يمكن أن يسبب انخفاض مستوى الكريات الحمراء الي امراض خطيره لذلك يجب استشاره الطبيب فورا "
                        )
                    )
                }
            }

            if (test.contains("Plt") || test.contains("Platelet") ||
                test.contains("Pit") || test.contains("PIt")
            ) {
                if (numberList[index] in 150.0..450.0) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(Plt)",
                            percentage = "${numberList[index]}",
                            testRange = "150.0 : 450.0",
                            testStatus = " نسبة الصفائح الدمويه الطبيعية  التي تتراوح بين 150 - 450  تشير إلى نطاق طبيعي ل الصفائح الدمويه"
                        )
                    )
                } else if (numberList[index] > 450.0) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(Plt)",
                            percentage = "${numberList[index]}",
                            testRange = "150.0 : 450.0",
                            testStatus = "نسبة الصفائح الدموية اعلي من المعدل الطبيعي الذي يتراوح بين (150 - 450) حيث الارتفاع يمكن أن يشير إلى بعض الأمراض المعروفة بتسببها في الالتهابات قد تؤدي إلى زيادة في عدد الصفائح الدمويه لذلك افضل زياره الطبيب لمعرفه سياق حالتك"
                        )
                    )
                } else {
                    tests.add(
                        MedicalTestModel(
                            testName = "(Plt)",
                            percentage = "${numberList[index]}",
                            testRange = "150.0 : 450.0",
                            testStatus = " نسبة الصفائح الدموية اقل من المعدل الطبيعي الذي يتراوح بين (150 - 450) حيث الانخفاض في نسبة الصفائح الدمويه أقل من الحد الأدنى، يمكن أن يزيد ذلك من خطر حدوث نزيف لذلك يجب استشاره الطبيب فورا "
                        )
                    )
                }
            }

            if (test.contains("WBCS") || test.contains("WBCs")) {
                if (numberList[index] in 3.6..11.0) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(WBCS)",
                            percentage = "${numberList[index]}",
                            testRange = "3.6 : 11.0",
                            testStatus = " نسبة الخلايا البيض الطبيعية  التي تتراوح بين 3.6 : 11.0  تشير إلى نطاق طبيعي ل الخلايا البيض"
                        )
                    )
                } else if (numberList[index] > 11.0) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(WBCS)",
                            percentage = "${numberList[index]}",
                            testRange = "3.6 : 11.0",
                            testStatus = "نسبة الخلايا البيض اعلي من المعدل الطبيعي الذي يتراوح بين ( 3.6 : 11.0 ) حيث الارتفاع يمكن أن يشير الإصابة بالعدوى، سواء كانت بكتيرية أو فيروسية لذلك افضل زياره الطبيب لمعرفه سياق حالتك"
                        )
                    )
                } else {
                    tests.add(
                        MedicalTestModel(
                            testName = "(WBCS)",
                            percentage = "${numberList[index]}",
                            testRange = "3.6 : 11.0",
                            testStatus = " نسبة الخلايا البيض اقل من المعدل الطبيعي الذي يتراوح بين ( 3.6 : 11.0 ) حيث الانخفاض أقل من الحد الأدنى، يمكن أن يزيد بعض الفيروسات يمكن أن تؤثر على نخاع العظم وتؤدي إلى انخفاض في عدد الخلايا البيض لذلك يجب استشاره الطبيب فورا "
                        )
                    )
                }
            }

            if (test.contains("TSH") || test.contains("Thyroid")) {
                if (numberList[index] in 0.27..4.94) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(TSH)",
                            percentage = "${numberList[index]}",
                            testRange = "0.27 : 4.94",
                            testStatus = " نسبة هرمون الغده الدرقيه الطبيعية  التي تتراوح بين 0.27 : 4.94  تشير إلى نطاق طبيعي ل الغده الدرقيه"
                        )
                    )
                } else if (numberList[index] > 4.94) {
                    tests.add(
                        MedicalTestModel(
                            testName = "(TSH)",
                            percentage = "${numberList[index]}",
                            testRange = "0.27 : 4.94",
                            testStatus = "نسبة هرمون الغده الدرقيه اعلي من المعدل الطبيعي الذي يتراوح بين ( 0.27 : 4.94) حيث الارتفاع يمكن أن يشير إلى انخفاض في وظيفة الغدة ويمكن أن يكون مؤشرًا على اضطراب في الغدة الدرقية يعرف بفرط النشاط الغدة الدرقية (هيبوثيرويدية ) لذلك افضل زياره الطبيب لمعرفه سياق حالتك"
                        )
                    )
                } else {
                    tests.add(
                        MedicalTestModel(
                            testName = "(TSH)",
                            percentage = "${numberList[index]}",
                            testRange = "0.27 : 4.94",
                            testStatus = " نسبة هرمون الغده الدرقيه اقل من المعدل الطبيعي الذي يتراوح بين ( 0.27 : 4.94 ) حيث الانخفاض أقل من الحد الأدنى، فهذا يشير إلى ارتفاع في نشاط الغدة فيجب اتخاذ الخطوات الضرورية للتشخيص والعلاج"
                        )
                    )
                }
            }


        }

        adapterMedicalTest.submitList(tests)

        binding.captureButton.isVisible = false
        binding.progress.isVisible = false
        // Print the results
        Log.d("testing Strings", "$stringList")
        Log.d("testing Numbers", "$numberList")


    }

    override fun onBackPressed() {
        val intent = Intent(this, BottomNavigationActivity::class.java)
        startActivity(intent)
        finish()
    }
}