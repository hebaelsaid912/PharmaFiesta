package com.example.pharmafiesta.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.pharmafiesta.R

private const val TAG = "PharmaFiestaWorker"

class PharmaFiestaWorker (context: Context, params: WorkerParameters) : Worker(context, params) {
    private val notificationList = listOf(
        "النوم الجيد يعزز الصحة العقلية والجسدية.",
        "تناول الفواكه والخضروات يمد الجسم بالفيتامينات والمعادن الأساسية.",
        "ممارسة التمارين الرياضية تقوي العضلات والعظام وتحسن اللياقة البدنية.",
        "الابتعاد عن التدخين يقلل من خطر الإصابة بالأمراض القلبية والسرطان.",
        "تناول وجبات صحية ومتوازنة يدعم الوزن الصحي ويقلل من خطر السمنة.",
        "شرب الماء بكميات كافية يساعد في ترطيب الجسم وتحسين وظائف الجهاز الهضمي.",
        " الابتعاد عن التوتر وممارسة تقنيات الاسترخاء يحسن الصحة العقلية.",
        "الحفاظ على نظافة اليدين يقلل من انتقال العدوى والأمراض.",
        "تناول الأسماك الدهنية مثل السلمون يزود الجسم بالأحماض الدهنية الأساسية.",
        " الابتعاد عن الأطعمة المصنعة والمشروبات الغازية يقلل من السكريات المضافة والدهون الضارة.",
        " الحفاظ على صحة العين من خلال التقيد بقواعد السلامة والاستراحة العينية الدورية.",
        "الحفاظ على صحة القلب من خلال مراقبة ضغط الدم والكولسترول وممارسة النشاط البدني.",
        "تجنب التعرض المفرط لأشعة الشمس واستخدام واقي الشمس للوقاية من أضرار الأشعة فوق البنفسجية.",
        "الحفاظ على صحة العظام من خلال تناول الكالسيوم وفيتامين د وممارسة التمارين المقاومة.",
        " القيام بفحوصات دورية والاستشارة الطبية المنتظمة للكشف المبكر عن الأمراض والمشاكل الصحية.",
        " تناول الأطعمة الكاملة والمكونة من الحبوب الكاملة يوفر الألياف والعناصر الغذائية الهامة.",
        ". الحفاظ على صحة الأسنان من خلال التنظيف اليومي وزيارة طبيب الأسنان بشكل منتظم",
        "ممارسة التأمل واليوغا يساعد في تقليل التوتر وتحسين الصحة العقلية",
        "اتباع نظام غذائي منخفض الصوديوم يساعد في السيطرة على ضغط الدم المرتفع",
        "الاستمرار في التعلم وتحفيز العقل يعززان القدرة العقلية ويقلل من خطر الخرف",
        "تقليل استهلاك السكر المضاف يقلل من خطر السمنة وأمراض القلب",
        "ممارسة الرياضات التنافسية والألعاب الجماعية تعزز العلاقات الاجتماعية وتحسن اللياقة البدنية",
        "تناول الأطعمة الغنية بالألياف يعزز الهضم ويساعد في الشعور بالشبع لفترة أطول",
        "الحفاظ على مستويات الكولسترول الصحية من خلال تجنب الدهون المشبعة والزيوت المهدرجة",
        "الحفاظ على صحة الدماغ من خلال التحدي العقلي وحل الألغاز والأنشطة الذهنية",
        "تناول اللبن ومنتجات الألبان القليلة الدسم يوفر الكالسيوم ويعزز صحة العظام",
        "الحفاظ على وزن صحي من خلال التوازن بين الطعام وممارسة النشاط البدني",
        " الاستمتاع بوقت الفراغ بأنشطة هادئة مثل القراءة أو الرسم لتحسين الصحة العقلية",
        "الاهتمام بالصحة العاطفية والاستثمار في العلاقات الإيجابية والدعم الاجتماعي",
        "النوم الجيد يساعد في تجديد الجسم وتعزيز الصحة العقلية",
        "استشر الطبيب أو الصيدلي قبل تناول أي دواء جديد",
        " تأكد من فهمك للجرعة الصحيحة وتوقيت تناول الدواء.",
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

    override fun doWork(): Result {
        Log.e(TAG, "WorkManager:: doWork::")
        val appContext = applicationContext
        return try {
            showNotification(notificationList.random(), appContext)
            Result.success()
        } catch (throwable: Throwable) {
            Log.e(TAG, "Error applying blur")
            Result.failure()
        }
    }

    private fun showNotification(title: String, context: Context) {
        val notificationID = Math.random().toInt()
        Log.e(TAG, "WorkManager:: notificationID:: $notificationID")

        // Create a notification channel for devices running Android Oreo or higher
        val channelId = "your_channel_id"
        val channelName = "Your Channel Name"
        val channelDescription = "Your Channel Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = channelDescription
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        // Create the notification
        val notificationBuilder = NotificationCompat.Builder(context, "your_channel_id")
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(title)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Show the notification
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            with(NotificationManagerCompat.from(context)) {
                notify(notificationID, notificationBuilder.build())
            }
        }
    }
}