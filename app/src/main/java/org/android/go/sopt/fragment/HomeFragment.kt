package org.android.go.sopt.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import org.android.go.sopt.MultiviewAdapter
import org.android.go.sopt.data.MULTI_TYPE1
import org.android.go.sopt.data.MULTI_TYPE2

import org.android.go.sopt.data.MultiData

import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.ResponseReqresDto

import org.android.go.sopt.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "binding is null" }

    private val reqresService = ServicePool.reqresService
    lateinit var multiAdapter: MultiviewAdapter
    val datas = mutableListOf<MultiData>()
    val dataList: MutableList<ResponseReqresDto.UserInfo> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

    }


    private fun initRecyclerView() {
        multiAdapter = MultiviewAdapter(requireContext())
        binding.rvHome.adapter = multiAdapter

        datas.apply {
            add(MultiData(MULTI_TYPE1, "Follower List", null))
        }
        multiAdapter.datas = datas
        multiAdapter.notifyDataSetChanged()

        loadData()
    }

    private fun loadData() {
        reqresService.getUsers().enqueue(object : Callback<ResponseReqresDto> {
            override fun onResponse(
                call: Call<ResponseReqresDto>,
                response: Response<ResponseReqresDto>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()?.data
                    for(user in users ?: emptyList()){
                        if (users != null) {
                            val userInfo = MultiData(
                                MULTI_TYPE2,
                                "${user.first_name} ${user.last_name}",
                                user.avatar
                            )
                            datas.add(userInfo)
                        }
                        multiAdapter.notifyDataSetChanged()
                    }

                } else {
                    Log.e(TAG, "Failed to load user data")
                }
            }

            override fun onFailure(call: Call<ResponseReqresDto>, t: Throwable) {
                Log.e(TAG, "Failed to load user data", t)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}