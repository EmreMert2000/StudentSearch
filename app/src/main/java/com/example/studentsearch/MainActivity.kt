package com.example.studentsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studentsearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.ButtonSearch.setOnClickListener {
            addStudentToDatabase()
        }
    }

    private fun addStudentToDatabase() {

        val studentName = binding.TextName.text.toString()
        val studentNumber = binding.TextID.text.toString()
        val studentScore = binding.TextPoint.text.toString().toInt()


        val dbHelper = DatabaseHelper(this)


        val isInserted = dbHelper.insertData(studentName,studentNumber, studentScore)

        // Ekleme işlemi başarılıysa mesaj gösterin, aksi takdirde bir hata mesajı gösterin
        if (isInserted) {
            Toast.makeText(this, "Öğrenci eklendi", Toast.LENGTH_SHORT).show()
            // Burada istediğiniz başka işlemler yapabilirsiniz
        } else {
            Toast.makeText(this, "Ekleme başarısız oldu", Toast.LENGTH_SHORT).show()
            // Hata yönetimi için gerekli işlemleri yapabilirsiniz
        }
    }
    private fun showData() {
        val dbHelper = DatabaseHelper(this)
        val studentData = dbHelper.selectData()

        val stringBuilder = StringBuilder()
        for (info in studentData) {
            stringBuilder.append(info).append("\n")
        }

        binding.textData.text = stringBuilder.toString()
    }
}
