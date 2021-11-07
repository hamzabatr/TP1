package fr.estia.net.batr.h.neighbors.data

import fr.estia.net.batr.h.neighbors.data.service.DummyNeighborApiService
import fr.estia.net.batr.h.neighbors.data.service.NeighborApiService
import fr.estia.net.batr.h.neighbors.models.Neighbor

class NeighborRepository {
    private val apiService: NeighborApiService

    init {
        apiService = DummyNeighborApiService()
    }

    fun getNeighbours(): List<Neighbor> = apiService.neighbours

    fun getNeighbour(position: Int): Neighbor = apiService.showNeighbour(position)

    fun removeNeighborFromList(neighbor: Neighbor) = apiService.deleteNeighbour(neighbor)

    fun createNeighbor(neighbor: Neighbor) = apiService.createNeighbour(neighbor)

    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}
