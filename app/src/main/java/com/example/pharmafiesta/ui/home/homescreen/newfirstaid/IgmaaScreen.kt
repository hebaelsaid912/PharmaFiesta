package com.example.pharmafiesta.ui.home.homescreen.newfirstaid

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.home.BottomNavDestinations
import com.example.pharmafiesta.ui.theme.Black
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.utils.GifScreen

@Composable
fun IgmaaScreen(navController: NavController) {

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

                    GifScreen("https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExM2NmczNobTUwcGo1cmpmNHFlY21tdm9xMWQyZnhod2ZtNTEwZjU5bSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/omU0SbWWVozhLo2nyM/giphy.gif")

                    GifScreen("https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExZDhqaGxsOHNkNnd1MWw2ZnNmY2Nmcnh4cjU4MXZmdnFjazk3cHRlOCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/Mun77Ukj9SjF8d9PeX/giphy.gif")

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "الاغماء (فقد الوعي )",
                        fontSize = 24.sp,
                        color = Black
                    )

                    Text(
                        text = "ان القاعدة العامة تقضي بعدم تحريك المصاب من مكانه وتقديم الاسعافات اللازمة في مكان تواجده ,الا أنه أحياناً يضطر المسعف الى اجلاء المصاب من مكانه و ذلك لحمايته أو حماية المسعف لنفسه وذلك على اعتبار ان هذا أهون الشرين للابتعاد عن مصدر الخطر",
                        fontSize = 16.sp,
                        color = Black
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = " وجود مسعف واحد",
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
                            text = "في حاله نقل المصاب \n" +
                                    "يجب الانتباه دائما إلى الحفاظ على استقامة الرأس والرقبة والجذع.\n" +
                                    "يعود المسعف إلى الوضعية الأولى ويكرر العملية.\n" +
                                    "إذا: الوضعية الأولى : ركبة مستندة على الأرض والرجل الأخرى عمودية على الأرض بحيث تشكل الساق مع الفخذ زاوية قائمة.\n" +
                                    "يتحرك المسعف إلى الخلف مع محافظته على وضعية الجذع المستقيمة.\n" +
                                    "الوضعية الثانية : عقب المسعف مستند على ساقه الممدودة على الأرض والرجل الأخرى تصبح فيها الزاوية بين الساق والفخذ منفرجة.\n",
                            fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ambulancer), modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentDescription = "ambulance"
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "وجود مسعفين اثنين",
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
                            text = "المسعف الأول يجثو أمام قدمي المصاب بحيث تكون ركبته اليمنى مثلاً على الأرض والرجل اليسرى عمودية على الأرض,يمسك قدمي المصاب ويرفعهما قليلاً.\n" +
                                    "المسعف الثاني يجثو أمام رأس المصاب بحيث يضع إحدى ركبتيه على الأرض أما الرجل الأخرى فتكون الزاوية فيها بين الساق و الفخذ منفرجة , يحيط رأس المصاب بكلتا يديه ويرفعه قليلا لحمايته أثناء السحب.\n" +
                                    "يجذب المسعف الأول المصاب إلى جهته لينتقل إلى الوضعية الثانية أما المسعف الثاني فينتقل إلى الوضعية الأولى وهو يحمي الرأس\n",
                            fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        painter = painterResource(id = R.drawable.twoambulancer),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentDescription = "two ambulance"
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "طريقة إخراج المصاب من السيارة",
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
                            text = "تستخدم عند مصاب عالق في سيارة ويوجد خطر على حياته فهنا يضطر المسعف إلى إخراجه رغم خطورة العملية حيث يخشى من حدوث كسر في العمود الفقري لدى المصاب لذلك يجب المحافظة على استقامة الرأس مع الجذع.\n" +
                                    "الطريقة:\n" +
                                    "يطبق مبدأ الحماية وذلك بوضع إشارات محددة لمكان الحادث لتنبيه السيارات القادمة كما يطفئ محرك سيارة المصاب ويرفع الفرامل.\n" +
                                    "يحرر قدمي المصاب من الدواسات,ويفك حزام الأمان.\n" +
                                    "يمرر ساعده تحت إبط المصاب من ناحية باب السيارة ويسند ذقنه براحة يده ,فيصبح رأس المصاب مقلوباً بعض الشيء إلى الوراء ومسنداً إلى كتف المسعف الأخرى.\n" +
                                    "يمرر المسعف ساعده الآخر تحت إبط المصاب التي من جهة السيارة الداخلية و يمسك بمعصم يد المصاب التي من جهة الباب.\n" +
                                    "يدير المصاب بحذر نحو الخارج منتبهاً إلى المحافظة على استقامة الرأس والرقبة والجذع\n",
                            fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "يبتعد عن السيارة وهو يسند جسم المصاب ثم يمدده بسرعة وهو ما زال يتجنب أي تحريك يسبب التواء العمود الفقري.",
                            fontSize = 16.sp,
                            color = Color.Red,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.outcarambulance),
                            modifier = Modifier
                                .height(100.dp),
                            contentDescription = "two ambulance"
                        )

                        Image(
                            painter = painterResource(id = R.drawable.fromcarambulance),
                            modifier = Modifier
                                .height(100.dp),
                            contentDescription = "two ambulance"
                        )

                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "ثم يبدأ بعدها المسعف بفحص تنفس و ضربات قلب المريض",
                        fontSize = 16.sp,
                        color = Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "فحص التنفس",
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
                            text = "إن فحص التنفس ضروري لدى المصاب بالغيبوبة وذلك لأنه عند حصول الغيبوبة يفقد الجهاز العصبي سيطرته الكاملة على كافة الأجهزة والأعضاء ومنها الجهاز العضلي فيحدث ارتخاء عضلي وما يهمنا هنا هو ارتداد قاعدة اللسان وإغلاقها للمجرى التنفسي لذا وجب فحص الوظيفة التنفسية وتفقدها.\n" +
                                    "الطريقة: يعمل المسعف على تحرير المسالك التنفسية بإمالة الرأس إلى الوراء وذلك بأن يضع إحدى يديه على جبين المصاب ويستخدم إصبعين من اليد الأخرى يوضعان على عظم الذقن (السبابة والوسطى)، كما يفك زر القميص وكذلك ربطة العنق والحزام.\n" +
                                    "يطبق المسعف تقنية (C.L.F) Check – Listen – feel) لفحص الوظيفة التنفسية وذلك بأن يضع أذنه فوق فم وأنف المصاب وهو ينظر إلى حركة الصدر والبطن\n",
                            fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "يـرى: يلاحظ المسعف حركة الجزء الأعلى من البطن وحركة الصدر.\n" +
                                    "يسمع: يسمع إذا كان الهواء يدخل ويخرج مع كل حركة تنفسية.\n" +
                                    "يشعر: يشعر بسخونة هواء الزفير الخارج من المريض واصطدامه بوجه المسعف.\n",
                            fontSize = 16.sp,
                            color = Green59,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "تفحص الوظيفة التنفسية ما بين 5-10ثواني. وفي حال كان المكان الموجود فيه المصاب مظلماً يضع المسعف يده على صدر المصاب عند تطبيق C.L.F.\n" +
                                    "أما عند الرضيع: يتم أولاً تحرير المسالك التنفسية وذلك بوضع منشفة أو ضمادات بين كتفي الرضيع، وذلك لأن كتلة الرأس عند الرضيع كبيرة فلا يمكن إمالته للخلف وتركه حيث يعود مباشرة إلى وضعه الأولي، ثم تطبق تقنية C.L.F.\n",
                            fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.examairambulance),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentDescription = "two ambulance"
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "فحص النبض",
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
                            text = "إن النبض الشرياني هو انعكاس لعمل القلب، فالقلب ينقبض وينبسط (يخفق) عدة مرات وهذه الخفقات تختلف حسب العمر وحسب الجنس وتخضع للعوامل الخارجية (رياضة-تعب) والداخلية (المرض) وكل خفقة تنتقل إلى الشرايين الرئيسية التي نستخدمها لقياس النبض.\n" +
                                    "يجب عند قياس النبض أن ننتبه إلى عدة أمور:\n" +
                                    "الانتظام – السرعة – التواتر.\n" +
                                    "يفحص النبض عند البالغ من الشريان السباتي في العنق بوضع السبابة والوسطى فقط دون الإبهام على مسار الشريان لمدة 5 ثواني إذا لم نجد النبض نقيسه من الجهة الأخرى لمدة 5 ثواني أيضاً.\n" +
                                    "يمكن قياس النبض من الشريان الكعبري في المعصم إلا أن السباتي أفضل. وعند الرضع يقاس النبض بنفس الطريقة السابقة ولكن من بين حلمتي الثدي\n",
                            fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "فحص الجهاز الدوراني",
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
                            text = "لملاحظة وجود نزيف عند المصاب، قد يكون النزف ظاهر وقد يكون باطن أي لم يظهر فوق ثياب المصاب، كما يتم فحص أسفل المصاب فقد يكون النزف غير ظاهر للعيان",
                            fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "فحص الجهاز المحرك",
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
                            text = "يتم فحص وجود كسور أو خلوع عند المصاب وذلك بجس العظام بواسطة أصابع المسعف وذلك بهدوء ولطف حتى لا نؤلم المصاب.\n" +
                                    "بعد أن يتم فحص المصاب بالتسلسل السابق يتم أيضاً تحديد عدد الإصابات ونوعية الإصابة وأماكن وجود الإصابات\n",
                            fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.exammanambulance),
                            modifier = Modifier
                                .height(100.dp),
                            contentDescription = "two ambulance"
                        )

                        Image(
                            painter = painterResource(id = R.drawable.examboyambulance),
                            modifier = Modifier
                                .height(100.dp),
                            contentDescription = "two ambulance"
                        )

                    }
                    Spacer(modifier = Modifier.height(15.dp))


                }

            }

        }

    }
}