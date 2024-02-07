package com.example.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.RepositorioHeroes
import com.example.superheroes.ui.theme.Shapes
import com.example.superheroes.ui.theme.SuperHeroesTheme

@Composable
fun CardHeroe(heroe: Hero, modifier: Modifier = Modifier){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.elevation)),
        modifier = modifier,
    ) {

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            ,verticalAlignment = Alignment.CenterVertically,){

            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(id = heroe.nameRes),
                    style = MaterialTheme.typography.displaySmall)
                Text(text = stringResource(heroe.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.space)))
            Box(modifier = Modifier
                .size(dimensionResource(id = R.dimen.image_size))
                .clip(Shapes.small)) {

                Image(painter = painterResource(heroe.imageRes)
                    , contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .clip(Shapes.small)
                        .size(dimensionResource(id = R.dimen.image_size))
                        )
            }

        }
    }
}

@Composable
fun ListaHeroes(heroes: List<Hero>, modifier: Modifier = Modifier, contentpaPadding : PaddingValues = PaddingValues(0.dp)
){

    LazyColumn(contentPadding = contentpaPadding){
        items(heroes){
            CardHeroe(heroe = it, modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_padding), 
                vertical = dimensionResource(id = R.dimen.vertical_padding)))
        }
    }
}


@Preview
@Composable
fun CardPreview(){
    val heroe1 = Hero(R.drawable.android_superhero1, R.string.hero1, R.string.description1)
    CardHeroe(heroe1)
}

@Preview
@Composable
fun ListaPreview(){
    SuperHeroesTheme(darkTheme = false) {

        ListaHeroes(heroes = RepositorioHeroes.heroes)
    }

}

@Preview
@Composable
fun ListaPreviewDark(){
    SuperHeroesTheme(darkTheme = true) {

        ListaHeroes(heroes = RepositorioHeroes.heroes)
    }
}

