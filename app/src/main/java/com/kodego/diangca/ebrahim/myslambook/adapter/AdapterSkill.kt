package com.kodego.diangca.ebrahim.myslambook.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.myslambook.databinding.RecycleViewItemRateBinding
import com.kodego.diangca.ebrahim.myslambook.model.Skill

class AdapterSkill(var context: Context, var skills: ArrayList<Skill>) :
RecyclerView.Adapter<AdapterSkill.SKillsViewHolder>(){


    inner class SKillsViewHolder(
        private val context: Context,
        private val itemBinding: RecycleViewItemRateBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        var skill = Skill()

        fun bindStudent(skill: Skill) {
            this.skill = skill
            itemBinding.itemName.text = "${skill.skill}"
            itemBinding.itemRate.text = "Rate: ${skill.rate}"

            itemBinding.btnRemove.setOnClickListener {
                btnRemoveOnClickListener(itemBinding, adapterPosition)
            }
        }

        override fun onClick(view: View?) {
            if (view!=null)
                Snackbar.make(
                    itemBinding.root,
                    "${itemBinding.itemName}",
                    Snackbar.LENGTH_SHORT
                ).show()
        }

    }

    private fun btnRemoveOnClickListener(itemBinding: RecycleViewItemRateBinding, positionAdapter: Int) {

        removeSong(itemBinding, positionAdapter)
    }

    private fun removeSong(itemBinding: RecycleViewItemRateBinding, positionAdapter: Int) {
        var alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Delete?")
        alertDialogBuilder.setMessage("Are you sure you want to delete ${skills[positionAdapter].skill}")
        alertDialogBuilder.setNegativeButton("Cancel", null)
        alertDialogBuilder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, position: Int) {
                skills.removeAt(positionAdapter)
                notifyItemRemoved(positionAdapter);
                notifyItemRangeChanged(positionAdapter, itemCount);
                Snackbar.make(
                    itemBinding.root,
                    "Song has been successfully removed.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }).show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SKillsViewHolder {
        val itemBinding =
            RecycleViewItemRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SKillsViewHolder(context, itemBinding)
    }

    override fun getItemCount(): Int {
        return skills.size
    }

    override fun onBindViewHolder(holder: SKillsViewHolder, position: Int) {
        holder.bindStudent(skills[position])
    }
}