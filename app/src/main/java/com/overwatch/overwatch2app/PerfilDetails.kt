package com.overwatch.overwatch2app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.overwatch.overwatch2app.databinding.ActivityPerfilDetailsBinding
import org.json.JSONObject
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View

class PerfilDetails : AppCompatActivity() {

    private var binding : ActivityPerfilDetailsBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val userDataSummary = intent.getStringExtra("Summary")
        val jsonObject = userDataSummary?.let { JSONObject(it) }

        setSupportActionBar(binding?.userToolBar)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = jsonObject!!.get("username").toString()
        }

        binding?.userToolBar?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.userImageDetails?.let { Glide.with(this).load(jsonObject!!.get("avatar")).into(it) }
        binding?.userNameCardDetails?.let { Glide.with(this).load(jsonObject!!.get("namecard")).into(it) }

        val privacyText = jsonObject?.get("privacy").toString()
        val spannableString = SpannableString(privacyText)
        spannableString.setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, privacyText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding?.profileStatusTV?.append(spannableString)

        if (privacyText.contains("pri")){
            binding?.privateUserText?.visibility = View.VISIBLE
            binding?.currentSeasonTV?.visibility = View.GONE
            binding?.competitiveText?.visibility = View.GONE
            binding?.CLRanksPC?.visibility = View.GONE
            binding?.competitiveTextConsole?.visibility = View.GONE
            binding?.CLRanksConsole?.visibility = View.GONE
        } else {
            //Datos competitivo en caso de tener perfil == public
            val compJsonObject = JSONObject(jsonObject!!.get("competitive").toString())

            val compPcJsonObject = compJsonObject.optJSONObject("pc")
            val compConsoleJsonObject = compJsonObject.optJSONObject("console")

            val privacyTextSeason = compPcJsonObject?.get("season").toString()
            val spannableStringSeason = SpannableString(privacyTextSeason)
            spannableStringSeason.setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, privacyTextSeason.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding?.currentSeasonTV?.append(spannableStringSeason)

            //Imagenes de los rangos en caso que no salga NULL
            //PC
            if (compPcJsonObject != null){
                val jsonTankObject = JSONObject(compPcJsonObject.get("tank").toString())
                val jsonDamageObject = JSONObject(compPcJsonObject.get("damage").toString())
                val jsonSupportObject = JSONObject(compPcJsonObject.get("support").toString())

                //TANQUE
                val imageTankPC = jsonTankObject.get("rank_icon").toString()
                binding?.imageRankTank?.let { Glide.with(this).load(imageTankPC).into(it) }
                binding?.rankTextTank?.text = "${jsonTankObject.get("division")} ${jsonTankObject.get("tier")}"

                //DAMAGE
                val imageDamagePC = jsonDamageObject.get("rank_icon").toString()
                binding?.imageRankDps?.let { Glide.with(this).load(imageDamagePC).into(it) }
                binding?.rankTextDps?.text = "${jsonDamageObject.get("division")} ${jsonDamageObject.get("tier")}"

                //SUPPORT
                val imageSupportPC = jsonSupportObject.get("rank_icon").toString()
                binding?.imageRankSupport?.let { Glide.with(this).load(imageSupportPC).into(it) }
                binding?.rankTextSupport?.text = "${jsonSupportObject.get("division")} ${jsonSupportObject.get("tier")}"
            } else {
                binding?.CLRanksPC?.visibility = View.GONE
                binding?.noUserFoundText?.visibility = View.VISIBLE
            }


            //CONSOLA
            if (compConsoleJsonObject != null) {
                val jsonTankObjectConsole = JSONObject(compConsoleJsonObject.get("tank").toString())
                val jsonDamageObjectConsole = JSONObject(compConsoleJsonObject.get("damage").toString())
                val jsonSupportObjectConsole = JSONObject(compConsoleJsonObject.get("support").toString())

                //CONSOLA
                val imageTankConsole = jsonTankObjectConsole.get("rank_icon").toString()
                binding?.imageRankTankConsole?.let { Glide.with(this).load(imageTankConsole).into(it) }
                binding?.rankTextTankConsole?.text = "${jsonTankObjectConsole.get("division")} ${jsonTankObjectConsole.get("tier")}"

            } else {
                binding?.CLRanksConsole?.visibility = View.GONE
                binding?.noUserFoundTextConsole?.visibility = View.VISIBLE
            }
        }
    }
}