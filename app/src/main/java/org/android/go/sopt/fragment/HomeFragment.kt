package org.android.go.sopt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.MultiviewAdapter
import org.android.go.sopt.R
import org.android.go.sopt.data.MultiData
import org.android.go.sopt.data.multi_type1
import org.android.go.sopt.data.multi_type2
import org.android.go.sopt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding : FragmentHomeBinding
        get() = requireNotNull(_binding) {"binding is null"}

    lateinit var multiAdapter : MultiviewAdapter
    val datas = mutableListOf<MultiData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { //이제 반환하는 view가 null일수 없으므로 ? 빼줌
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //대부분 로직 여기 구현
        //null이 아닌 context 반환이 requireContext
        /*binding.rvHome.adapter= MyAdapter(requireContext())
        //fragment 에서는 this불가하므로 context
        binding.rvHome.layoutManager = LinearLayoutManager(context)*/
        initRecyclerView()


    }
    private fun initRecyclerView() {
        multiAdapter = MultiviewAdapter(requireContext())
        binding.rvHome.adapter = multiAdapter

        datas.apply {
            add(MultiData(multi_type1, "안드로이드 과제", null, null, null))
            for (i in 1..10) {
                add(MultiData(multi_type2, null, image = R.drawable.dog, "subin$i", "kim"))
            }
            multiAdapter.datas = datas
            multiAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}