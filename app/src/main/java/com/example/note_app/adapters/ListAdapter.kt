package com.example.note_app.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note_app.R
import com.example.note_app.SplashScreen
import com.example.note_app.databinding.ListRowBinding
import com.example.note_app.databinding.PopUpBinding
import com.example.note_app.models.NoteModel
import com.example.note_app.objects.SplashControl
import com.example.note_app.realm.DB

class ListAdapter(private val arrList: ArrayList<NoteModel>): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    class MyViewHolder(val bind:ListRowBinding):RecyclerView.ViewHolder(bind.root){ }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bind = ListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(bind)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = arrList.get(position)
        holder.bind.apply {
            rBaslik.text = item.baslik
            rIcerik.text = item.icerik

            listCard.setOnClickListener {
                val upBaslik = item.baslik
                val upIcerik = item.icerik
                val id = item.noteId

                val binding = PopUpBinding.inflate(LayoutInflater.from(holder.bind.root.context))
                val popup = AlertDialog.Builder(holder.bind.root.context)

                binding.popBaslik.setText(upBaslik)
                binding.popIcerik.setText(upIcerik)

                popup.setTitle("Not Güncelleme işlemi!")
                popup.setNegativeButton("İptal", DialogInterface.OnClickListener { dialogInterface, i -> })
                popup.setPositiveButton("Evet",DialogInterface.OnClickListener { dialogInterface, i ->
                    val db = DB(holder.bind.root.context)

                    val baslik = binding.popBaslik.text
                    val icerik = binding.popIcerik.text

                    db.updateNote(id,baslik.toString(),icerik.toString())
                    notifyItemChanged(position)
                })

                popup.setView(binding.root)
                popup.show()
            }

            listCard.setOnLongClickListener {

                val id = item.noteId
                val alert = AlertDialog.Builder(holder.bind.root.context)
                alert.setTitle("Not Silme işlemi!")
                alert.setMessage("Kayıtlı olan bu notu kalıcı olarak silmek istediğinizden emin misiniz?")
                alert.setNegativeButton("İptal", DialogInterface.OnClickListener { dialogInterface, i -> })
                alert.setPositiveButton("Evet",DialogInterface.OnClickListener { dialogInterface, i ->
                    val db = DB(holder.bind.root.context)
                    db.deleteNote(id)
                    arrList.removeAt(position)
                    notifyItemChanged(position)
                })
                alert.show()
                true
            }
        }
    }

    override fun getItemCount(): Int {
       return arrList.size
    }
}