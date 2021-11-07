package fr.estia.net.batr.h.neighbors.adapters

import fr.estia.net.batr.h.neighbors.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
    fun getString(string: String)
}