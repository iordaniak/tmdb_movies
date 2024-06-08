package com.example.tmdbmovies.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tmdbmovies.ui.composable.MoviesList
import com.example.tmdbmovies.ui.model.MovieModel

var popularDummyList = listOf(
    MovieModel(
        1,
        "12 Angry Men",
        "The defense and the prosecution have rested and the jury is filing into the jury room to decide if a young Spanish-American is guilty or innocent of murdering his father. What begins as an open and shut case soon becomes a mini-drama of each of the jurors' prejudices and preconceptions about the trial, the accused, and each other.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/ppd84D2i9W8jXmsyInGyihiSyqz.jpg"
    ),
    MovieModel(
        2,
        "Pulp Fiction",
        "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg"
    ),
    MovieModel(
        3,
        "Spirited Away",
        "A young girl, Chihiro, becomes trapped in a strange new world of spirits. When her parents undergo a mysterious transformation, she must call upon the courage she never knew she had to free her family.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg"
    ),
    MovieModel(
        4,
        "Parasite",
        "All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg"
    ),
    MovieModel(
        5,
        "Cinema Paradiso",
        "A filmmaker recalls his childhood, when he fell in love with the movies at his village's theater and formed a deep friendship with the theater's projectionist.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/8SRUfRUi6x4O68n0VCbDNRa6iGL.jpg"
    ),
    MovieModel(
        6,
        "Django Unchained",
        "With the help of a German bounty hunter, a freed slave sets out to rescue his wife from a brutal Mississippi plantation owner.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/7oWY8VDWW7thTzWh3OKYRkWUlD5.jpg"
    ),
    MovieModel(
        7,
        "The Shining",
        "Jack Torrance accepts a caretaker job at the Overlook Hotel, where he, along with his wife Wendy and their son Danny, must live isolated from the rest of the world for the winter. But they aren't prepared for the madness that lurks within.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/nRj5511mZdTl4saWEPoj9QroTIu.jpg"
    ),
    MovieModel(
        8,
        "Inglorious Basterds",
        "In Nazi-occupied France during World War II, a group of Jewish-American soldiers known as \"The Basterds\" are chosen specifically to spread fear throughout the Third Reich by scalping and brutally killing Nazis. The Basterds, lead by Lt. Aldo Raine soon cross paths with a French-Jewish teenage girl who runs a movie theater in Paris which is targeted by the soldiers.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/7sfbEnaARXDDhKm0CZ7D7uc2sbo.jpg"
    ),
    MovieModel(
        9,
        "Amadeus",
        "Wolfgang Amadeus Mozart is a remarkably talented young Viennese composer who unwittingly finds a fierce rival in the disciplined and determined Antonio Salieri. Resenting Mozart for both his hedonistic lifestyle and his undeniable talent, the highly religious Salieri is gradually consumed by his jealousy and becomes obsessed with Mozart's downfall, leading to a devious scheme that has dire consequences for both men.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/1n5VUlCqgmVax1adxGZm8oCFaKc.jpg"
    ),
    MovieModel(
        10,
        "Requiem for a Dream",
        "The hopes and dreams of four ambitious people are shattered when their drug addictions begin spiraling out of control. A look into addiction and how it overcomes the mind and body.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/nOd6vjEmzCT0k4VYqsA2hwyi87C.jpg"
    ),
    MovieModel(
        11,
        "Vertigo",
        "A retired San Francisco detective suffering from acrophobia investigates the strange activities of an old friend's wife, all the while becoming dangerously obsessed with her.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/15uOEfqBNTVtDUT7hGBVCka0rZz.jpg"
    ),
    MovieModel(
        12,
        "A Clockwork Orange",
        "In a near-future Britain, young Alexander DeLarge and his pals get their kicks beating and raping anyone they please. When not destroying the lives of others, Alex swoons to the music of Beethoven. The state, eager to crack down on juvenile crime, gives an incarcerated Alex the option to undergo an invasive procedure that'll rob him of all personal agency. In a time when conscience is a commodity, can Alex change his tune?",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/4sHeTAp65WrSSuc05nRBKddhBxO.jpg"
    ),
    MovieModel(
        13,
        "Snatch",
        "Unscrupulous boxing promoters, violent bookmakers, a Russian gangster, incompetent amateur robbers and supposedly Jewish jewelers fight to track down a priceless stolen diamond.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/56mOJth6DJ6JhgoE2jtpilVqJO.jpg"
    ),
    MovieModel(
        14,
        "There Will Be Blood",
        "Ruthless silver miner, turned oil prospector, Daniel Plainview, moves to oil-rich California. Using his son to project a trustworthy, family-man image, Plainview cons local landowners into selling him their valuable properties for a pittance. However, local preacher Eli Sunday suspects Plainview's motives and intentions, starting a slow-burning feud that threatens both their lives.",
        7.62,
        isFavored = false,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/fa0RDkAlCec0STeMNAhPaF89q6U.jpg"
    )
)
var favoriteDummyList = listOf(
    MovieModel(
        7,
        "The Shining",
        "Jack Torrance accepts a caretaker job at the Overlook Hotel, where he, along with his wife Wendy and their son Danny, must live isolated from the rest of the world for the winter. But they aren't prepared for the madness that lurks within.",
        7.62,
        isFavored = true,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/nRj5511mZdTl4saWEPoj9QroTIu.jpg"
    ),
    MovieModel(
        8,
        "Inglorious Basterds",
        "In Nazi-occupied France during World War II, a group of Jewish-American soldiers known as \"The Basterds\" are chosen specifically to spread fear throughout the Third Reich by scalping and brutally killing Nazis. The Basterds, lead by Lt. Aldo Raine soon cross paths with a French-Jewish teenage girl who runs a movie theater in Paris which is targeted by the soldiers.",
        7.62,
        isFavored = true,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/7sfbEnaARXDDhKm0CZ7D7uc2sbo.jpg"
    ),
    MovieModel(
        9,
        "Amadeus",
        "Wolfgang Amadeus Mozart is a remarkably talented young Viennese composer who unwittingly finds a fierce rival in the disciplined and determined Antonio Salieri. Resenting Mozart for both his hedonistic lifestyle and his undeniable talent, the highly religious Salieri is gradually consumed by his jealousy and becomes obsessed with Mozart's downfall, leading to a devious scheme that has dire consequences for both men.",
        7.62,
        isFavored = true,
        "https://www.themoviedb.org/t/p/w94_and_h141_bestv2/1n5VUlCqgmVax1adxGZm8oCFaKc.jpg"
    )
)

@Composable
fun TmdbMoviesApp(
    modifier: Modifier = Modifier
){
    MoviesList(moviesList = popularDummyList)
}
@Preview
@Composable
fun TmdbMoviesAppPreview(){
    TmdbMoviesApp()
}

