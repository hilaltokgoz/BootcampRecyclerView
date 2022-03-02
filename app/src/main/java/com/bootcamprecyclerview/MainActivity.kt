package com.bootcamprecyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamprecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DogsAdapter.SelectedDogListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dogList = arrayListOf( //liste veritürü olarak <DogModel> girilebilir.
            DogModel(R.drawable.avustralyacoban, "Avusturalya Çoban"),
            DogModel(R.drawable.danua, "Danua"),
            DogModel(R.drawable.golden, "Golden"),
            DogModel(R.drawable.husky, "Husky"),
            DogModel(R.drawable.jackrussellterrier, "Jackrussellterrier"),
            DogModel(R.drawable.leonberger, "Leonberger")
        )
        //Adapter Bağlama
        val dogsAdapter = DogsAdapter(dogList, this) // adapter değişken içinde mevcut.
        //reverseLayout=liste alttan üste mi üstten alta doğru mu dizilsin.
        //1.LinearLayout ile yapımı
        binding.dogRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        //2.Grid Layout ile yapımı
        //  binding.dogRecyclerView.layoutManager=GridLayoutManager(applicationContext,2,GridLayoutManager.VERTICAL,false)
        //3.StaggerGridLayout ile
        //   İlgili View ın boyutuna göre düzensiz bir şekilde sıralar.
        //Pinterest'teki konu cardview ları örnek verilebilir.
        //  binding.dogRecyclerView.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.dogRecyclerView.adapter = dogsAdapter //RecyclerView deki adapter ile oluşturulan adapter'ı eşleştirdik.
        binding.dogRecyclerView.setHasFixedSize(true) //görünümü düzenle ,topla

        //onItemClick
       dogsAdapter.onItemClick = ::selectedDogOnClick



    }
      fun selectedDogOnClick(getSelectedDog:DogModel){
          Log.e("Selected Dog OnClick:",getSelectedDog.dogKind)
      }



    override fun selectDog(dog: DogModel) {
        Log.e("Selected Dog Interface:", dog.dogKind) //Logcat de seçilen kopek türleri görülebilir oldu.
    }
}