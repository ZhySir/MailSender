package com.zhy.mailsenderdemo

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zhy.mailsender.Mail
import com.zhy.mailsender.MailSender
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity(), MailSender.OnMailSendListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 发送普通邮件
        btnSendMail.setOnClickListener {
            val mail = getMail()
            MailSender.getInstance().sendMail(mail, this)
        }

        // 发送带附件的邮件
        btnSendMailWithFile.setOnClickListener {
            val mail = getMail()

            val file = File(getExternalFilesDir("file")!!.absolutePath + File.separator + "MailSender.txt")
            var os: OutputStream? = null
            try {
                os = FileOutputStream(file)
                val str = "hello world"
                val data = str.toByteArray()
                os.write(data)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    os?.close()
                } catch (e: IOException) {
                }
            }
            mail.attachFiles = arrayListOf(file)

            MailSender.getInstance().sendMail(mail, this)
        }

    }

    private fun getMail(): Mail {
        return Mail().apply {

            // 发件箱服务器地址
            mailServerHost = etServerHost.text.toString()
            // 发件箱服务器端口
            mailServerPort = etServerHostPort.text.toString()
            // 发件邮箱地址
            fromAddress = etFromAddress.text.toString()
            // 发件邮箱授权码
            password = etFromPwd.text.toString()
            // 收件箱列表
            toAddress = arrayListOf(etToAddress.text.toString())
            // 邮件主题
            subject = "MailSender测试邮件"

            // 发送纯文本内容的邮件
            // content = "MailSender Android快速实现发送邮件"

            // 发送html内容的邮件
//            content = """
//                <p1 style = "color: red">MailSender</p1><br/>
//                <p1 style = "color: blue">Android快速实现发送邮件</p1><br/>
//                <p1 style = "color: blue">https://github.com/ZhySir/MailSender</p1><br/>
//                <p6 style = "color: gray">这是html内容的邮件</p1>
//            """

            // 发送SpannableString内容的邮件
            content = SpanUtils(this@MainActivity)
                .appendLine("MailSender").setFontSize(28, true).setForegroundColor(Color.RED)
                .appendLine("Android快速实现发送邮件")
                .appendLine("https://github.com/ZhySir/MailSender").setForegroundColor(Color.BLUE)
                .appendLine("这是SpannableString内容的邮件")
                .setForegroundColor(Color.parseColor("#888888")).setFontSize(12, true)
                .create()
        }
    }

    override fun onSuccess() {
        Toast.makeText(this@MainActivity, "发送成功", Toast.LENGTH_SHORT).show()
    }

    override fun onError(e: Throwable) {
        Toast.makeText(this@MainActivity, "发送失败：$e.message", Toast.LENGTH_SHORT).show()
    }

}
