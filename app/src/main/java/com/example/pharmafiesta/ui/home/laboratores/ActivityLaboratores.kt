package com.example.pharmafiesta.ui.home.laboratores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pharmafiesta.databinding.ActivityLaboratoresBinding
import com.example.pharmafiesta.ui.home.BottomNavigationActivity
import com.example.pharmafiesta.ui.home.laboratores.adapter.AdapterLabs
import com.example.pharmafiesta.ui.home.laboratores.model.ModelLabs
import com.example.pharmafiesta.utils.onTextChange

class ActivityLaboratores : AppCompatActivity() {

    lateinit var binding : ActivityLaboratoresBinding

    private val adapterLabs by lazy { AdapterLabs() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLaboratoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        fillLabs()
    }

    private fun initView() {
        binding.rvLabs.adapter = adapterLabs

        binding.edSearch.onTextChange {
            if(it.isNotEmpty()) {
                val newList = java.util.ArrayList<ModelLabs>()
                for (i in adapterLabs.currentList) {
                    if (i.labNearFrom?.contains(it) == true ||
                        i.labName?.contains(it) == true) {
                        newList.add(i)
                    }
                }
                adapterLabs.submitList(newList)
            }else{
                fillLabs()
            }
        }
    }

    private fun fillLabs() {

        val labs = ArrayList<ModelLabs>()

        labs.add(ModelLabs("معمل المختبر",
            "محافظه القاهره بجوار كشري التحرير",
            "012003311445",
            "القاهره - ميدان التحرير - كشري التحرير - الاوبرا - المهندسين"))

        labs.add(ModelLabs("معمل المختبر",
            "محافظه القاهره  - الدقي",
            "012004491445",
            "القاهره - ميدان التحرير - كشري التحرير - الاوبرا - المهندسين - الدقي"))

        labs.add(ModelLabs("معمل البرج",
            "محافظه القاهره  - العجوزه",
            "012114491345",
            "القاهره - المهندسين - الدقي - العجوزه"))

        labs.add(ModelLabs("معمل البرج",
            "محافظه الجيزه  -امام نادي الصيد",
            "012799497845",
            " نادي الصيد - الجيزه - الزمالك - الهرم"))

        labs.add(ModelLabs("معمل ترست لاب",
            "محافظه الجيزه  -امام المترو",
            "012124497845",
            " مترو البحوث - الجيزه - فيصل - الهرم"))

        labs.add(ModelLabs("معمل ترست لاب",
            "محافظه القاهره  - المعادي",
            "012104497845",
            " مترو المعادي - القاهره - الزهراء - التجمع الخامس - حلوان"))

        labs.add(ModelLabs("معمل ميجا لاب",
            "محافظه القاهرة الجديدة بجوار مترو الانفاق ",
            "0128874497845",
            " الزهراء - التجمع الخامس - حلوان"))

        labs.add(ModelLabs("معمل الريان ",
            "محافظه المنوفيه  - شبين الكوم امام مستشفي الهلال",
            "01007392616",
            "المنوفيه – شبين الكوم  - الجامعه - القصر"))

        labs.add(ModelLabs("معمل الجودة",
            "محافظه المنوفيه  - شارع الاستاد – برج البداية",
            "01065021721",
            "المنوفيه – شبين الكوم - الاستاد - الجلاء"))

        labs.add(ModelLabs("معمل المختبر الحديث ",
            "محافظه المنوفيه  - شبين الكوم –ميت خاقان –ميدان عايده",
            "01023310217",
            "المنوفيه – شبين الكوم  - الجامعه – ميت خاقان"))

        labs.add(ModelLabs("معمل أبراج ",
            "محافظه المنوفيه  - شبين الكوم- الكوبري العلوي امام سيف للسياحه- برج العاصمه",
            "01006920248",
            "المنوفيه – شبين الكوم  - قبلي - رانين"))

        labs.add(ModelLabs("معمل وقايه ",
            "محافظه المنوفيه  - شبين الكوم - امام مستشفي القصر",
            "01020237351",
            "المنوفيه – شبين الكوم  - الجامعه - القصر"))

        labs.add(ModelLabs("معمل الرحاب ",
            "محافظه المنوفيه  - شبين الكوم – الحي الغربي شارع جبانه الاقباط –بجوار مسجد القرط",
            "01020237351",
            "المنوفيه – شبين الكوم  - الحي الغربي "))

        labs.add(ModelLabs("معمل الفا ",
            "محافظه المنوفيه  - شبين الكوم – شارع جمال عبد الناصر – ميدان شرف",
            "01200790828",
            "المنوفيه – شبين الكوم  - الميدان -  ميدان شرف"))

        labs.add(ModelLabs("معمل عز لاب  ",
            "محافظه المنوفيه  - شبين الكوم – ميدان شرف - البرج الذهبي",
            "01200555562",
            "المنوفيه – شبين الكوم  - الميدان – ميدان شرف"))

        labs.add(ModelLabs("معمل مؤمن  ",
            "محافظه المنوفيه  - شبين الكوم – ميدان شرف ",
            "0482327850",
            "المنوفيه – شبين الكوم  - الميدان – ميدان شرف"))

        labs.add(ModelLabs("معمل الاسكندريه ",
            "محافظه الاسكندريه   - المنتز – العصافرة1- ش مسجد الهادي ",
            "035363504",
            "الاسكندريه – المنتزه  - العصافره – سيدي جابر "))

        labs.add(ModelLabs("معمل الفا   ",
            "محافظه المنوفيه  - قويسنا – شارع بورسعيد امام الثانويه بنات القديمه",
            "01554190836",
            "المنوفيه – قويسنا  - الميدان – "))

        labs.add(ModelLabs("معمل الثقه  ",
            "محافظه المنوفيه  - قويسنا–الشارع العمومي بعد قسم الشرطه",
            "01006303574",
            "المنوفيه –قويسنا- قسم الشرطه –الميدان "))

        labs.add(ModelLabs("معمل سفير  ",
            "محافظه المنوفيه  - شبين الكوم – ميدان شرف –شارع حتحوت ",
            "01013000897",
            "المنوفيه – شبين الكوم  - الميدان – ميدان شرف"))

        labs.add(ModelLabs("معمل اليسر  ",
            "محافظه المنوفيه  -قويسنا – الشارع العمومي –بجوار صيدليه ميدو ",
            "01010189187",
            "المنوفيه – قويسنا- الميدان – الشارع العمومي "))

        adapterLabs.submitList(labs)

    }

    override fun onBackPressed() {
        val intent = Intent(this, BottomNavigationActivity::class.java)
        startActivity(intent)
        finish()
    }

}