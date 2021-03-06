package com.bootcamprecyclerview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamprecyclerview.databinding.DogCardDesignBinding

// Code with ❤️
//┌──────────────────────────┐
//│ Created by Hilal TOKGOZ  │
//│ ──────────────────────── │
//│ hilaltokgoz98@gmail.com  │
//│ ──────────────────────── │
//│ 1.03.2022                │
//└──────────────────────────┘

class DogsAdapter(private var dogsList:ArrayList<DogModel>,private  val selectedDogListener: SelectedDogListener):RecyclerView.Adapter<DogsAdapter.DogCardDesign>() {
    //Higher order function ile adapter ı main activity e bağlama.
    var onItemClick:(DogModel) -> Unit={}


    //Adapter ile view i bağlamak için kullanılır.
//View'ın bağlanacağı yer ViewHolder.
class DogCardDesign(val dogCardDesignBinding: DogCardDesignBinding): RecyclerView.ViewHolder(dogCardDesignBinding.root)

    //fragment'ta layoutInlater  ile binding bağlama işlemine benzer.
    //Yukarıdan çağırılan binding ile xml eşleştirilir.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardDesign {
        val  layoutInflater=LayoutInflater.from(parent.context)
        val dogCardDesignBinding=DogCardDesignBinding.inflate(layoutInflater,parent,false)
        return  DogCardDesign(dogCardDesignBinding)   //view lar bağlandı.

    }
    //Tasarımda oluşmasını istediğimiz her card'ın kodlandığı alan
    //Her item'ın kontrol edileceği kısım
    //Card ların sayısının çoğaltıldığı yer.
    override fun onBindViewHolder(holder: DogCardDesign, position: Int) {

       val dog=dogsList[position] //ArrayList'in size'ı kadar ilerler.0 dan size a kadar.

       holder.dogCardDesignBinding.dogImageView.setImageResource(dog.dogImage)
       holder.dogCardDesignBinding.dogKindtextView.text= dog.dogKind

    /*   holder.dogCardDesignBinding.dogImageView.setOnClickListener{
            //kopek resmine tıklanınca yapılacaklar.
           selectedDogListener.selectDog(dog) //Adpter dan activity e gönderdi.
       }
    */
        //Interface use
        holder.dogCardDesignBinding.root.setOnClickListener {
            //Cardview içinde herhangi bir yere basılınca ne olacak.
            selectedDogListener.selectDog(dog)
        }
        //Higher Order Function use
        holder.dogCardDesignBinding.root.setOnClickListener {
            onItemClick(dog)
            //Silme İşlemi Adapter'dan
            dogsList.removeAt(position)
            dogsListUpdate(dogsList)

        }
    }
    //Kaç adet view'ın oluştuğunu belirttiğimiz kısım
    /* Aşağıdaki tanımlamayla aynı.
    override fun getItemCount(): Int {
    return dogsList.size
    }
    */
    override fun getItemCount(): Int=dogsList.size

//ekleyip silme
   fun dogsListUpdate(updateList:ArrayList<DogModel>){
       dogsList=updateList
       notifyDataSetChanged() //veri değişiikliğini algıla
   }




    //Adapter dan activity'e veri aktarımı için;
    interface SelectedDogListener{
        fun selectDog(dog:DogModel) //Taşınılacak değişken
    }

}