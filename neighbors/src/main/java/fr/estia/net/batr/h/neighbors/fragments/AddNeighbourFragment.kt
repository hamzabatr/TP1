package fr.estia.net.batr.h.neighbors.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.fragment.app.Fragment
import fr.estia.net.batr.h.neighbors.NavigationListener
import fr.estia.net.batr.h.neighbors.data.NeighborRepository
import fr.estia.net.batr.h.neighbors.databinding.AddNeighborBinding
import fr.estia.net.batr.h.neighbors.models.Neighbor
import android.text.TextUtils
import android.util.Patterns
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.estia.net.batr.h.neighbors.R


class AddNeighbourFragment : Fragment(), TextWatcher {

    private lateinit var binding: AddNeighborBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as? NavigationListener)?.updateTitle(R.string.ajouter_voisin)

        binding = AddNeighborBinding.inflate(inflater, container, false)
        with(binding) {
            registerButton.isEnabled = false
            nom.addTextChangedListener(this@AddNeighbourFragment)
            image.addTextChangedListener(this@AddNeighbourFragment)
            adress.addTextChangedListener(this@AddNeighbourFragment)
            tel.addTextChangedListener(this@AddNeighbourFragment)
            moi.addTextChangedListener(this@AddNeighbourFragment)
            website.addTextChangedListener(this@AddNeighbourFragment)
        }

        binding.registerButton.setOnClickListener {

            val neighbor = Neighbor(
                (NeighborRepository.getInstance().getNeighbours().size + 1).toLong(),
                binding.nom.text.toString(),
                binding.image.text.toString(),
                binding.adress.text.toString(),
                binding.tel.text.toString(),
                binding.moi.text.toString(),
                true,
                binding.website.text.toString()
            )
            NeighborRepository.getInstance().createNeighbor(neighbor)
            (activity as? NavigationListener)?.showFragment(ListNeighborsFragment())
            Toast.makeText(
                context,
                context?.getString(R.string.neighbor_added),
                Toast.LENGTH_LONG
            ).show()
        }

        return binding.root
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        with(binding) {

            if (tel.text.toString().isEmpty()) {
                telLyt.isErrorEnabled = false
            } else {
                if ((tel.text.toString().startsWith("06") || tel.text.toString()
                        .startsWith("07")) /*&& tel.text.toString().length >= 2*/ && tel.text.toString().length == 10
                ) {
                    telLyt.isErrorEnabled = false
                } else {
                    telLyt.error = context?.getString(R.string.forme_correcte)
                }
            }

            if (adress.text.toString().isEmpty()) {
                adressLyt.isErrorEnabled = false
            } else {
                if (isValidEmail(adress.text.toString())) {
                    adressLyt.isErrorEnabled = false

                } else {
                    adressLyt.error = getString(R.string.mail_invalide)
                }
            }

            if (website.text.toString().isEmpty()) {
                websiteLyt.isErrorEnabled = false
            } else {
                if (URLUtil.isValidUrl(website.text.toString())) {
                    websiteLyt.isErrorEnabled = false
                } else {
                    websiteLyt.error = context?.getString(R.string.site_invalide)
                }
            }

            if (image.text.toString().isEmpty()) {
                imageLyt.isErrorEnabled = false
                uploadImageUrlImmediately(R.drawable.ic_baseline_person_add_24.toString())
            } else {
                if (URLUtil.isValidUrl(image.text.toString())) {
                    imageLyt.isErrorEnabled = false
                    uploadImageUrlImmediately(image.text.toString())
                } else {
                    imageLyt.error = context?.getString(R.string.image_invalide)
                    uploadImageUrlImmediately(R.drawable.ic_baseline_person_add_24.toString())
                }
            }

            if (!telLyt.isErrorEnabled && !websiteLyt.isErrorEnabled && !imageLyt.isErrorEnabled && !adressLyt.isErrorEnabled) {
                registerButton.isEnabled = !(nom.text.toString().isEmpty() ||
                        image.text.toString().isEmpty() ||
                        adress.text.toString().isEmpty() ||
                        tel.text.toString().isEmpty() ||
                        moi.text!!.isEmpty() ||
                        website.text!!.isEmpty()
                        )
            } else {
                registerButton.isEnabled = false
            }
        }

    }

    private fun uploadImageUrlImmediately(urlImage: String) {

        Glide.with(binding.img.context)
            .load(urlImage)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_baseline_person_outline_24)
            .error(R.drawable.ic_baseline_person_outline_24)
            .skipMemoryCache(false)
            .into(binding.img)

    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}



