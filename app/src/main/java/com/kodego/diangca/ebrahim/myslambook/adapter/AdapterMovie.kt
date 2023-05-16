package com.kodego.diangca.ebrahim.myslambook.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.myslambook.databinding.RecycleViewSingleItemBinding
import com.kodego.diangca.ebrahim.myslambook.model.Movie

class AdapterMovie(var context: Context, var movie: ArrayList<Movie>) :
RecyclerView.Adapter<AdapterMovie.MovieViewHolder>(){


    inner class MovieViewHolder(
        private val context: Context,
        private val itemBinding: RecycleViewSingleItemBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        var movie = Movie()

        fun bindStudent(movie: Movie) {
            this.movie = movie
            itemBinding.itemName.text = "${movie.movieName}"

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

    private fun btnRemoveOnClickListener(itemBinding: RecycleViewSingleItemBinding, positionAdapter: Int) {

        removeSong(itemBinding, positionAdapter)
    }

    private fun removeSong(itemBinding: RecycleViewSingleItemBinding, positionAdapter: Int) {
        var alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Delete?")
        alertDialogBuilder.setMessage("Are you sure you want to delete ${movie[positionAdapter].movieName}")
        alertDialogBuilder.setNegativeButton("Cancel", null)
        alertDialogBuilder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, position: Int) {
                movie.removeAt(positionAdapter)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding =
            RecycleViewSingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(context, itemBinding)
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindStudent(movie[position])
    }
}