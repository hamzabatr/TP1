package fr.estia.net.batr.h.neighbors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.estia.net.batr.h.neighbors.NavigationListener
import fr.estia.net.batr.h.neighbors.data.NeighborRepository
import fr.estia.net.batr.h.neighbors.databinding.ShowNeighborBinding
import fr.estia.net.batr.h.neighbors.models.Neighbor
import fr.estia.net.batr.h.neighbors.R.*


class ShowNeighborFragment : Fragment(){
    private lateinit var binding: ShowNeighborBinding
    private var position: String? = ""
    private lateinit var neighbor: Neighbor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as? NavigationListener)?.updateTitle(string.ajouter_voisin)
        binding = ShowNeighborBinding.inflate(inflater, container, false)

        position = arguments?.getString("string")
        neighbor = NeighborRepository.getInstance().getNeighbour(NeighborRepository.getInstance().getNeighbours().size - position!!.toInt() -1)

        with(binding) {

            Glide.with(binding.img.context)
                .load(neighbor.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.img)

            nomValue.text = neighbor.name
            telValue.text = neighbor.phoneNumber
            websiteValue.text = neighbor.webSite
            adressValue.text = neighbor.address
            moiValue.text = neighbor.aboutMe
        }

        binding.returnButton.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(ListNeighborsFragment())
        }



        return binding.root
    }
}