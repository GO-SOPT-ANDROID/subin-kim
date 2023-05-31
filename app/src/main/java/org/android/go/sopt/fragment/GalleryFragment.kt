package org.android.go.sopt.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import coil.load
import org.android.go.sopt.GalleryViewModel
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.util.ContentUriRequestBody


class GalleryFragment : Fragment() {
    private var viewModel: GalleryViewModel = GalleryViewModel()

    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) { "binding is null" }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(maxItems = 3)) { imageUriList: List<Uri> ->
            with(binding) {
                when (imageUriList.size) {
                    0 -> {
                        Toast.makeText(requireContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }

                    1 -> {
                        viewModel.setRequestBody(
                            ContentUriRequestBody(
                                requireContext(),
                                imageUriList[0]
                            )
                        )
                        ivGalleryFirst.load(imageUriList[0])
                        viewModel.uploadImage()
                    }

                    2 -> {
                        ivGalleryFirst.load(imageUriList[0])
                        ivGallerySecond.load(imageUriList[1])
                    }

                    3 -> {
                        ivGalleryFirst.load(imageUriList[0])
                        ivGallerySecond.load(imageUriList[1])
                        ivGalleryThird.load(imageUriList[2])
                    }

                    else -> {
                        Toast.makeText(requireContext(), "3개까지의 이미지만 선택해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }

    private val locatePermissionLancher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireContext(), "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGalleryPickImage.setOnClickListener {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
            binding.btnGalleryPickImage.setOnClickListener {
                locatePermissionLancher.launch("android.permission.ACCESS_COARSE_LOCATION")
            }
        }
//        binding.pagerGallery.adapter = GalleryAdapter().apply {
//
//            setItemList(listOf(R.drawable.dog,R.drawable.dog,R.drawable.dog))
//
//        }


    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}