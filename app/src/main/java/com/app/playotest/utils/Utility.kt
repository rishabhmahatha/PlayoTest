package com.app.playotest.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.app.ProgressDialog


object Utility {
    /**
     * Adds the Fragment into layout container
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param currentFragment             Current loaded Fragment to be hide
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param requiredAnimation           true if screen transition animation is required
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(IllegalStateException::class)
    fun addFragment(
        fragmentManager: FragmentManager,
        fragmentContainerResourceId: Int,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        commitAllowingStateLoss: Boolean
    ): Boolean {
        if (currentFragment == null || nextFragment == null) {
            return false
        }

        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(
            fragmentContainerResourceId,
            nextFragment,
            nextFragment.javaClass.simpleName
        )
        fragmentTransaction.addToBackStack(nextFragment.javaClass.simpleName)

        val parentFragment = currentFragment.parentFragment
        fragmentTransaction.hide(parentFragment ?: currentFragment)

        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitAllowingStateLoss()
        }

        return true
    }

    /**
     * Replaces the Fragment into layout container
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param fragmentManager             FRAGMENT MANGER
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param requiredAnimation           true if screen transition animation is required
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(IllegalStateException::class)
    fun replaceFragment(
        fragmentContainerResourceId: Int,
        fragmentManager: FragmentManager?,
        nextFragment: Fragment?,
        commitAllowingStateLoss: Boolean
    ): Boolean {
        if (nextFragment == null || fragmentManager == null) {
            return false
        }
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(
            fragmentContainerResourceId,
            nextFragment,
            nextFragment.javaClass.simpleName
        )

        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitAllowingStateLoss()
        }

        return true
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun isNetworkAvailable(activity: Activity): Boolean {
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    lateinit var progress: ProgressDialog
    fun showProgressBar(activity: Activity) {
        progress = ProgressDialog(activity)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()
    }

    fun hideProgressBar() {
        if (progress.isShowing) {
            progress.dismiss()
        }
    }

}