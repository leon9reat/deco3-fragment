package com.medialink.deco3fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class OptionDialogFragment : DialogFragment(), View.OnClickListener {
    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rdgOptions: RadioGroup
    private lateinit var radSaf: RadioButton
    private lateinit var radMou: RadioButton
    private lateinit var radLvg: RadioButton
    private lateinit var radMoyes: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rdgOptions = view.findViewById(R.id.rdg_options)
        radSaf = view.findViewById(R.id.rad_saf)
        radLvg = view.findViewById(R.id.rad_lvg)
        radMou = view.findViewById(R.id.rad_mou)
        radMoyes = view.findViewById(R.id.rad_moyes)

        btnChoose.setOnClickListener(this)
        btnClose.setOnClickListener(this)
    }

    interface OnOptionDialogListener {
        fun onOptionChoosen(text: String?)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_close -> dialog?.cancel()
            R.id.btn_choose -> {
                val checkedRadioButtonId = rdgOptions.checkedRadioButtonId
                if (checkedRadioButtonId != -1) {
                    var coach: String? = null
                    when (checkedRadioButtonId) {
                        R.id.rad_saf -> coach = radSaf.text.toString().trim()
                        R.id.rad_mou -> coach = radMou.text.toString().trim()
                        R.id.rad_lvg -> coach = radLvg.text.toString().trim()
                        R.id.rad_moyes -> coach = radMoyes.text.toString().trim()
                    }

                    optionDialogListener?.onOptionChoosen(coach)
                    dialog?.dismiss()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        if (fragment is DetailCategoryFragment) {
            val detailCategoryFragment = fragment as DetailCategoryFragment?
            this.optionDialogListener = detailCategoryFragment?.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }
}
