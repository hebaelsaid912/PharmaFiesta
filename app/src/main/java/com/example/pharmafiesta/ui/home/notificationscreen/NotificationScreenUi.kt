package com.example.pharmafiesta.ui.home.notificationscreen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.theme.Green59

private const val TAG = "NotificationScreenUi"

@Composable
fun NotificationScreenUi (navController: NavController) {
     val notificationList = listOf(
        "النوم الجيد يعزز الصحة العقلية والجسدية.",
        "تناول الفواكه والخضروات يمد الجسم بالفيتامينات والمعادن الأساسية.",
        "ممارسة التمارين الرياضية تقوي العضلات والعظام وتحسن اللياقة البدنية.",
        "الابتعاد عن التدخين يقلل من خطر الإصابة بالأمراض القلبية والسرطان.",
        "تجنب التعرض المفرط لأشعة الشمس واستخدام واقي الشمس للوقاية من أضرار الأشعة فوق البنفسجية.",
        "الحفاظ على صحة العظام من خلال تناول الكالسيوم وفيتامين د وممارسة التمارين المقاومة.",
        " القيام بفحوصات دورية والاستشارة الطبية المنتظمة للكشف المبكر عن الأمراض والمشاكل الصحية.",
        " تناول الأطعمة الكاملة والمكونة من الحبوب الكاملة يوفر الألياف والعناصر الغذائية الهامة.",
        "اقرأ واتبع دائمًا التعليمات الموجودة على العبوة أو النشرة الداخلية للدواء.",
        "لا تقم بتجاوز الجرعة الموصى بها أو تغيير الجرعة دون استشارة الطبيب.",
        "احتفظ بقائمة بالأدوية التي تتناولها وتأكد من مشاركتها مع الأطباء والصيادلة.",
        " تجنب تناول الأدوية المنتهية الصلاحية أو التي تغيرت في اللون أو الرائحة.",
        " اقرأ التحذيرات والتدابير الاحترازية المذكورة على العبوة واتبعها.",
        "لا تتوقف عن تناول الدواء الموصوف دون استشارة الطبيب، حتى إذا شعرت بتحسن.",
        "تجنب تناول الأدوية المتعارضة مع بعضها البعض، واطلب نصيحة الطبيب أو الصيدلي حول التفاعلات الدوائية المحتملة.",
        "لا تشارك الأدوية الخاصة بك مع الآخرين، حتى وإن كانوا يعانون من نفس الأعراض.",
        "حافظ على تخزين الأدوية في مكان جاف وبارد، وبعيدًا عن متناول الأطفال.",
        "استشر الطبيب حول أي تأثيرات جانبية تشعر بها بعد تناول الدواء.",
        "لا تقم بتغيير نوع الدواء الموصوف دون استشارة الطبيب.",
        "قد تحتاج إلى مراقبة مستويات الدواء في الجسم من خلال تحاليل الدم المنتظمة، اتبع توجيهات الطبيب.",
        "إذا كنت تواجه صعوبة في تناول الأدوية الصلبة، استشر الصيدلي حول صيغ بديلة مثل السائل أو القرص الممزق."
    )
    Column(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 30.dp, start = 20.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigateUp() }
            )

            Text( modifier = Modifier
                .padding(top = 30.dp, start = 10.dp),text = "Notifications",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp)
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(notificationList){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 8.dp)
                        .border(BorderStroke(1.dp, Green59), RoundedCornerShape(8.dp)),
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth().padding(6.dp)
                    ) {
                        Text(
                            text = it,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
