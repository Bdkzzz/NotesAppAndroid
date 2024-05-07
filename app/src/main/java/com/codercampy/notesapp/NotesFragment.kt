package com.codercampy.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.codercampy.notesapp.databinding.FragmentNotesBinding
import com.codercampy.notesapp.db.Note

class NotesFragment : Fragment() {

    companion object {

        private const val ADD_NOTE_RESULT = 1267

    }

    private lateinit var binding: FragmentNotesBinding

    private lateinit var noteSaver: NoteSaver
    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteSaver = NoteSaver(requireContext())

        setFragmentResultListener("note") { _, bundle ->
            val note = bundle.getParcelable<Note>("note") ?: return@setFragmentResultListener
            addNote(note)
        }

        adapter = MyAdapter(object : MyAdapterListener {
            override fun onItemDelete(pos: Int, note: Note) {
                deleteNote(pos, note)
            }
        })
        binding.recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            navigateToAddScreen()
        }

        //load all the saved notes
        val savedNotes = noteSaver.getAllNotes()
        if (savedNotes.isEmpty()) {
            binding.animationView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.animationView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            adapter.addNotes(savedNotes)
        }

    }

    private fun deleteNote(pos: Int, note: Note) {
        noteSaver.deleteNote(note)
        adapter.deleteNote(pos)

        if (adapter.itemCount == 0) {
            binding.animationView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }

    private fun navigateToAddScreen() {
        findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToNoteAddFragment())
    }

    private fun addNote(note: Note) {
        adapter.addNote(note)
        noteSaver.saveNote(note)

        if (adapter.itemCount > 0) {
            binding.animationView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

}