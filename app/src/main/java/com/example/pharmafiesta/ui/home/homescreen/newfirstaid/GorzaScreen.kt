package com.example.pharmafiesta.ui.home.homescreen.newfirstaid

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.home.BottomNavDestinations
import com.example.pharmafiesta.ui.theme.Black
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.utils.GifScreen
import com.example.pharmafiesta.utils.webViewCompose.navigateToWebView

@Composable
fun GorzaScreen(navController:NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopStart)
                    .clickable { navController.navigateUp() }
            )

        }

        LazyColumn(
            modifier = Modifier
            //  .fillMaxSize()
            //   .padding(16.dp)
        ) {
            item {
                Column(/*modifier = Modifier.fillMaxWidth(),*/
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "الخياطة (الغرز)",
                        fontSize = 24.sp,
                        color = Black
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "نصائح عامة",
                            fontSize = 18.sp,
                            color = Color.Red
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "المحافظة على الجرح نظيف وجاف قدر الإمكان لمدة 24 إلى 48 ساعة الأولى بعد وضع الغرز.\n" +
                                    "ترك الضمادات على الجرح خلال  24 ساعة الأولى.\n" +
                                    "اتباع تعليمات الغسل والتجفيف وتغير الضمادة المقدمة من قبل مقدم الرعاية الطبية.\n" +
                                    "فحص الجرح والغرز يوميًا لملاحظة أي تغييرات عليه.\n" +
                                    "التأكد من نظافة يديك عند العناية بالجرح.\n" +
                                    "أخذ أدوية لتخفيف الألم وفقًا لتوجيهات الطبيب.\n" +
                                    "المتابعة مع مقدم الرعاية الصحية للتأكد من التئام الجرح بشكل صحيح.\n" +
                                    "استخدام الكريمات أو المراهم الموصى بها من قبل الطبيب."
                            ,fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "في حال إصابة الطفل بحروق",
                            fontSize = 18.sp,
                            color = Color.Red
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "يمكن أن تكون الحروق خطيرة وتهدد حياة الطفل ولكن في حالات نادرة.\n" +
                                    "تعد الحروق شائعة بين الأطفال، ويجب التعامل مع الحروق بشكل صحيح حيث يمكن للإسعافات الأولية أن تؤثر على نتائج الحروق.\n" +
                                    "تعد أفضل طريقة أن يتم وضع الحرق تحت الماء البارد لمدة لا تقل عن 10 دقائق لإخراج الحرارة من المنطقة المصابة.\n" +
                                    "يفضل أن يتم تغطية المنطقة المصابة والعمل على حماية الجلد من الجراثيم، ويجب التوجه إلى المستشفى في حال إصابة الأطفال بالحروق البالغة.\n" +
                                    "في حال الإصابة بنوبة حمى:\n" +
                                    "قد تكون عملية تنظيم درجة الحرارة في جسم الأطفال أصعب لذلك في حال الإصابة بالحمى وكانت درجة حرارة جسم الطفل عالية جداً، يمكن أن يصاب الطفل بنوبة حمى شديدة. ويمكن أن تظهر العلامات على شكل جلد ساخن وتراجع في العيون ويبدأ الطفل بالقيام بحركات مضطربة قليلاً. أفضل الطرق للتخفيف من أعراض نوبة الحمى تبريد جسم الطفل عن طريق تخفيف الملابس وفتح النوافذ، ويجب تجنب البطانيات المبلولة حيث يمكن أن تسبب الصدمة لجسم الطفل، ويجب إعطاء الطفل الكثير من السوائل ويمكن أن يتم إعطائه بعض الأدوية مثل الباراسيتامول."
                            ,fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "في حال اختناق الطفل",
                            fontSize = 18.sp,
                            color = Color.Red
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "في حال كان الطفل لا يسعل ولا يبكي ويصدر صوت يشبه الغرغرة وكنت تشك بأن طفلك يختنق، يجب القيام بما يلي:"
                            ,fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "الطريقة الأولى ",
                            fontSize = 18.sp,
                            color = Color.Red
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "رفع الطفل ووضعه على ركبتيك وجعل وجهه لأسفل\n" +
                                    "يجب أن تكون أقدامه أعلى من رأسه حتى ينزلق الشيء الذي يسبب الاختناق\n" +
                                    "يمكن أن تساهم الجاذبية في هذه العملية\n" +
                                    "كما يساهم القيء في التخلص من المسبب للاختناق\n" +
                                    "يجب أن يتم الضرب على الكتف حيث تسبب الضربة اهتزاز مجرى الهواء وبالتالي ضغط الهواء إلى خارج الجسم وخروج المسبب للاختناق\n" +
                                    "قد يشعر بعض الآباء بعدم الراحة لضرب أطفالهم الرضع ولكن يمكن لهذه العملية أن تنقذ الطفل من الاختناق المحتوم.\n" +
                                    "يمكن أن يتم تكرار الضربة من ثلاث إلى خمس مرات للتخلص من المسبب للاختناق.\n" +
                                    "الطريقة الثانية:\n" +
                                    "في حال لم تجدي هذه الطريقة يمكن أن يتم قلب الطفل على ظهره ودعم رأسه واستخدام إصبعين والضغط على وسط الصدر.\n" +
                                    "حيث يسبب الضغط على الصدر السعال الصناعي الذي يجبر الهواء على الخروج من الرئتين وبالتالي دفع الهواء إلى الخارج وخروج المسبب للاختناق.\n" +
                                    "في حال كان عمر الطفل أكبر من عام يفضل اتباع نفس الطريقة لكن مع مراعاة حجم الطفل.\n" +
                                    "يمكن اللجوء إلى الضغط على البطن تحت الأضلاع.\n" +
                                    "في حال ابتلاع الطفل لأشياء لا يجب ابتلاعها:\n" +
                                    "في حال تناول الطفل مواد تنظيف أو أدوية أوغيرها، يجب نقل الطفل إلى المستشفى في أقرب وقت ممكن، ويجب محاولة معرفة ما تم ابتلاعه ليتم علاجه بناءً على نوع المادة التي تم تناولها."
                            ,fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "في حال اصطدام رأس الطفل",
                            fontSize = 18.sp,
                            color = Color.Red
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text("يمكن أن يتعرض الطفل إلى ارتطام رأسه في محاولته للمشي أو الزحف\n" +
                                "يمكن أن يتم معالجة معظم الحالات عن طريق وضع الثلج على المنطقة المصابة\n" +
                                "لكن قد يحتاج الطفل إلى المزيد من الرعاية في حال ظهرت عليه أعراض أخرى مثل القيء والنعاس وتغير في السلوك\n" +
                                "في حال ظهرت على الطفل مثل هذه الأعراض التي تدل على سوء حالة الطفل يجب التوجه إلى المستشفى لعلاج"
                            ,fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    
                    Image(modifier = Modifier.fillMaxWidth(),painter = painterResource(id = R.drawable.gorazadvice), contentDescription = "goraz")

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            navController.navigateToWebView(
                                BottomNavDestinations.BaseHomeScreen.WebViewScreen.route,
                                "https://www.moh.gov.sa/awarenessplateform/FirstAid/Pages/Stitches.aspx")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Green59)
                    ) {
                        androidx.compose.material.Text(
                            text = "Show Source",
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))


                }
            }

        }

    }

}