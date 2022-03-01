package com.bootcamprecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamprecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      val dogList=arrayListOf( //liste veritürü olarak <DogModel> girilebilir.
          DogModel(R.drawable.avustralyacoban,"Avusturalya Çoban"),
          DogModel(R.drawable.danua,"Danua"),
          DogModel(R.drawable.golden,"Golden"),
          DogModel(R.drawable.husky,"Husky"),
          DogModel(R.drawable.jackrussellterrier,"Jackrussellterrier"),
          DogModel(R.drawable.leonberger,"Leonberger")
      )
      val dogsAdapter=DogsAdapter(dogList) // adapter değişken içinde mevcut.
        //reverseLayout=liste alttan üste mi üstten alta doğru mu dizilsin.
        //LinearLayout ile yapımı
    //  binding.dogRecyclerView.layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        //Grid Layout ile yapımı
        binding.dogRecyclerView.layoutManager=GridLayoutManager(applicationContext,2,GridLayoutManager.VERTICAL,false)
      binding.dogRecyclerView.adapter=dogsAdapter //RecyclerView deki adapter ile oluşturulan adapter'ı eşleştirdik.
        binding.dogRecyclerView.setHasFixedSize(true) //görünümü düzenle ,topla
    }
}