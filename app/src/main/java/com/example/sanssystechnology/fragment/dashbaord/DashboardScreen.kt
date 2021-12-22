package com.example.sanssystechnology.fragment.dashbaord

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.get
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sanssystechnology.*
import com.example.sanssystechnology.databinding.FragmentDashboardScreenBinding
import com.example.sanssystechnology.fragment.*
import com.example.sanssystechnology.fragment.dashbaord.viewmodal.DashBoardViewModal
import com.example.sanssystechnology.fragment.websitepackage.WebsitePackages
import com.example.sanssystechnology.modal.Item
import com.example.sanssystechnology.recyclerview.HomeRecyclerView
import com.example.sanssystechnology.util.Session
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class DashboardScreen : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private var title :String ="Home"
    private lateinit var session:Session
    private var _binding: FragmentDashboardScreenBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: HomeRecyclerView
    lateinit var modal:DashBoardViewModal

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentDashboardScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        session = Session(this.requireActivity())
        binding.navView.setNavigationItemSelectedListener(this)
        modal = ViewModelProvider(this).get(DashBoardViewModal::class.java)
        setOnClick()
        intiallizeRecyclerView()
        backStack()
        println("called onece")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            binding.navView.menu.findItem(R.id.home).itemId -> {
                title ="Home"
               // binding.layputToolbar.appName.setText(title)
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
            }
            binding.navView.menu.findItem(R.id.contact).itemId -> {
                title ="Contact"
                //binding.layputToolbar.appName.setText(title)
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, ContactScreen())
                    .addToBackStack("contact")
                    .commit()
            }
            binding.navView.menu.findItem(R.id.about).itemId -> {
                Snackbar.make(binding.root, "About button is clicked", Snackbar.LENGTH_SHORT).show()
                title ="About"
                //binding.layputToolbar.appName.setText(title)
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, AboutScreen())
                    .addToBackStack("about")
                    .commit()
            }
            binding.navView.menu.findItem(R.id.signOut).itemId -> logout()
            binding.navView.menu.findItem(R.id.web).itemId -> {
                //Snackbar.make(binding.root, "About button is clicked", Snackbar.LENGTH_SHORT).show()
                //title ="About"
                //binding.layputToolbar.appName.setText(title)
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, WebsitePackages())
                    .addToBackStack("package_web")
                    .commit()
            }
            binding.navView.menu.findItem(R.id.seo).itemId -> {
                //Snackbar.make(binding.root, "About button is clicked", Snackbar.LENGTH_SHORT).show()
                //title ="About"
                //binding.layputToolbar.appName.setText(title)
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, SeoPackage())
                    .addToBackStack("package_web")
                    .commit()
            }

            binding.navView.menu.findItem(R.id.webDevelopment).itemId -> {
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, WebDevelopmentScreen())
                    .addToBackStack("web_Development")
                    .commit()
            }
            binding.navView.menu.findItem(R.id.app_gameDevelopment).itemId -> {
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, AppGameDevelopmentScreen())
                    .addToBackStack("game")
                    .commit()
            }
            binding.navView.menu.findItem(R.id.softwarDevelopment).itemId -> {
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, SoftwareDevelopmentScreen())
                    .addToBackStack("software")
                    .commit()
            }
            binding.navView.menu.findItem(R.id.digitalmarketing).itemId -> {
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, DigitalMarketingScreen())
                    .addToBackStack("digital")
                    .commit()
            }
            binding.navView.menu.findItem(R.id.hardware_solution).itemId -> {
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, HardwareSolution())
                    .addToBackStack("hardware")
                    .commit()
            }
            binding.navView.menu.findItem(R.id.security).itemId -> {
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, SecuityScreen())
                    .addToBackStack("secuity")
                    .commit()
            }
            binding.navView.menu.findItem(R.id.training).itemId -> {
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, ComingSoon())
                    .addToBackStack("training")
                    .commit()
            }
        }
        return true
    }
    private fun logout() {
        AlertDialog.Builder(this.requireContext())
            .setTitle("Logout")
            .setMessage("Are you sure you want to LogOut !!")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, i ->
                var session = Session(requireContext())
                session.setLoggedIn(
                    loggedIn = false,
                    email = " ",
                    password = "",
                    username = "",
                    id = ""
                )
                session.removeAll()
                GoogleSignIn
                    .getClient(this.requireActivity(),
                        GoogleSignInOptions
                            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .build()).signOut().addOnCompleteListener(this.requireActivity(), OnCompleteListener<Void?> {
                        requireActivity()
                            .supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.container, LoginScreen())
                            .commit()
                    })
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, _ ->
                dialogInterface.dismiss()
            }).show()
    }

    private fun backStack() {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(requireActivity(), object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if(requireActivity().supportFragmentManager.backStackEntryCount == 1) {
                        val builder = AlertDialog.Builder(requireContext())
                        builder.setCancelable(false)
                        builder.setMessage("Do you want to Exit?")
                        builder.setPositiveButton("Yes") { dialog, which -> //if user pressed "yes", then he is allowed to exit from application
                            requireActivity().finish()
                        }
                        builder.setNegativeButton("No") { dialog, which -> //if user select "No", just cancel this dialog and continue with app
                            dialog.cancel()
                        }.create().show()
                    } else {
                        requireActivity().supportFragmentManager.popBackStack()
                    }

                }
            }
            )
    }

    private fun intiallizeRecyclerView() {
        binding.swipeRefreshlayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            binding.swipeRefreshlayout.isRefreshing = false
        })
        val manager= GridLayoutManager(context,1)
        adapter = HomeRecyclerView(requireContext())
        binding.baseAdpater.layoutManager = manager
        binding.baseAdpater.adapter = adapter
        adapter.setData(modal.courseList)
    }

    fun setOnClick() {
        binding.layputToolbar.appName.text  = title
        binding.layputToolbar.expandedMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.LEFT)
        }
    }

    override fun onResume() {
        super.onResume()
        title ="Home"
        binding.layputToolbar.appName.setText(title)
    }


}