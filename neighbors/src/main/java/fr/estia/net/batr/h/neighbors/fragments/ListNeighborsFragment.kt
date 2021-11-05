package fr.estia.net.batr.h.neighbors.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.estia.net.batr.h.neighbors.NavigationListener
import fr.estia.net.batr.h.neighbors.R
import fr.estia.net.batr.h.neighbors.adapters.ListNeighborHandler
import fr.estia.net.batr.h.neighbors.adapters.ListNeighborsAdapter
import fr.estia.net.batr.h.neighbors.data.NeighborRepository
import fr.estia.net.batr.h.neighbors.models.Neighbor


class ListNeighborsFragment : Fragment(), ListNeighborHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNeighbor: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as? NavigationListener)?.updateTitle(R.string.list_voisin)

        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        addNeighbor = view.findViewById(R.id.add_button)

        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(AddNeighbourFragment())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbours().reversed()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter

    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(context?.getString(R.string.delete_neighbor))
                .setPositiveButton(
                    context?.getString(R.string.yes)
                ) { dialog, _ ->
                    NeighborRepository.getInstance().removeNeighborFromList(neighbor)
                    dialog.dismiss()
                    refreshPage()
                }
                .setNegativeButton(
                    getString(R.string.cancel)
                ) { _, _ ->

                }
            builder.create().show()
        }
    }

    private fun refreshPage() {
        val neighbors = NeighborRepository.getInstance().getNeighbours().reversed()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter
    }
}