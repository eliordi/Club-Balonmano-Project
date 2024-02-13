package es.jcc.project.MainApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.jcc.project.Adapters.NoticiasAdapter
import es.jcc.project.Classes.Noticia
import es.jcc.project.R
import es.jcc.project.databinding.FragmentHomeBinding
import es.jcc.project.databinding.FragmentTeamsBinding


class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // inflater.inflate(R.layout.fragment_home, container, false)

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {

        val noticias = mutableListOf<Noticia>(
            Noticia(R.drawable.noticia1, "Derrotas para el #PrimeraNacional y #2aN masc. Los de Vicent Nogues en Granollers y\n" +
                    "los de Adria Machi en casa contra el Marni no fueron capaces de mantener en la 2a parte el nivel de la 1a.\n" +
                    "No podemos dar tantas facilidades a nuestros rivales.  ¡¡¡A seguir trabajando!!!"),
            Noticia(R.drawable.noticia2, "\uD83D\uDCE2¡¡¡Nuestro torneo de Semana Santa ya está en proceso!!! \uD83D\uDC9B\uD83E\uDD45\n" +
                    "\uD83D\uDC4CYa hay equipos interesados. Los principales objetivos siguen siendo fomentar la convivencia, disfrutar de la práctica del balonmano y la competición.\n" +
                    "\uD83D\uDCC5 ✏️¡¡Apuntad las fechas!! Marzo 2024\n" +
                    "#BmCiutatdAlgemesi2024"),
            Noticia(R.drawable.noticia3, "Foto del entrenamiento de ayer de nuestros chicos del senior con Iker\n" +
                    "que ya se recupera en casa de su intervención del domingo.\n" +
                    "#ForçaIker \uD83D\uDC9B #CorGroguet"),
            Noticia(R.drawable.noticia4, "#1aNacionalFem \uD83E\uDD3E\u200D♀️ J6\n" +
                    "\uD83E\uDD45 CH L\\'Alcúdia \n" +
                    "@cbmmaristasalge\n" +
                    "29 \uD83C\uDD9A 28 Agustinos\n" +
                    "\uD83C\uDFA4 Salva Esquer \"Duelo de los 2 primeros clasificados. \n" +
                    "Partido en que en todo momento fuimos por delante y al final se complicó. Buena actitud, \n" +
                    "las jugadoras han luchado por cada balón.\"  \uD83D\uDC9B \uD83D\uDDA4")
        )

        val noticiasAdapter = NoticiasAdapter(this.requireContext(), noticias)
        binding.recView3?.adapter = noticiasAdapter
        binding.recView?.layoutManager = LinearLayoutManager(this.requireContext(),
            LinearLayoutManager.VERTICAL, false)
        binding.recView?.addItemDecoration(DividerItemDecoration(this.requireContext(), DividerItemDecoration.VERTICAL))
    }

}