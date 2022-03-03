package com.bootcamprecyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamprecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DogsAdapter.SelectedDogListener {
    private lateinit var binding: ActivityMainBinding

    private  var selectedDog: DogModel?=null
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

        //Add/Delete Data in RecyclerView
        //Listeye 2 adet veri gönderdik.
        binding.addFAB.setOnClickListener {
            dogList.add(DogModel(R.drawable.pomsky,"Pomsky"))
            dogList.add(DogModel(R.drawable.pug,"Pug"))
            dogsAdapter.dogsListUpdate(dogList)
        }
        binding.deleteFAB.setOnClickListener {
            //Normal veritabanında id çağrılıp id ile eşdeğer değer silinir.
          //Şuan gönderilen ögeye göre sildireceğiz.
            val deleteIndex=dogList.indexOf(selectedDog)
            dogList.removeAt(deleteIndex)
            dogsAdapter.dogsListUpdate(dogList)
//Bunun yanı sıra adapter dan da silme işlemi yaptırılabilir.
        }





    }
      fun selectedDogOnClick(getSelectedDog:DogModel){
          Log.e("Selected Dog OnClick:",getSelectedDog.dogKind)

          selectedDog=getSelectedDog

      }



    override fun selectDog(getSelectedDog: DogModel) {
        Log.e("Selected Dog Interface:", getSelectedDog.dogKind) //Logcat de seçilen kopek türleri görülebilir oldu.

        selectedDog=getSelectedDog

    }
}