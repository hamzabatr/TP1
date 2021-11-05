package fr.estia.net.batr.h.neighbors.adapters

import androidx.fragment.app.Fragment
import fr.estia.net.batr.h.neighbors.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
}