package ru.plumsoftware.marvel.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import coil.compose.AsyncImage
import ru.plumsoftware.marvel.R
import ru.plumsoftware.data.model.uimodel.Hero
import ru.plumsoftware.marvel.ui.theme.MarvelTheme
import ru.plumsoftware.marvel.ui.theme.Sizes
import ru.plumsoftware.marvel.ui.theme.Spaces

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SnapList(
    modifier: Modifier = Modifier,
    list: MutableList<Hero>,
    onScroll: (Color) -> Unit = {},
    onHeroClick: (Hero) -> Unit = {}
) {

    val state = rememberLazyListState()
    val layoutDirection = LocalLayoutDirection.current

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val firstVisibleItemIndex =
                    state.layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: -1
                val lastVisibleItemIndex =
                    state.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1

                if (firstVisibleItemIndex != -1) {

                    val color = if (firstVisibleItemIndex == 0) {
                        if (lastVisibleItemIndex == 1) {
                            list[firstVisibleItemIndex].heroColor
                        } else {
                            list[1].heroColor
                        }
                    } else {
                        list[firstVisibleItemIndex + 1].heroColor
                    }

                    onScroll(Color(color))
                }
                return super.onPreScroll(available, source)
            }
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .nestedScroll(connection = nestedScrollConnection)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        state = state,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
    ) {
        itemsIndexed(list) { _, item ->
            Spacer(modifier = Modifier.width(width = Spaces.Items.snapRowItemSpace))
            Button(
                shape = MaterialTheme.shapes.medium,
                contentPadding = PaddingValues(all = Sizes.HeroButton.allPadding),
                modifier = Modifier
                    .width(width = Sizes.HeroButton.width)
                    .height(height = Sizes.HeroButton.height),
                onClick = {
                    onHeroClick(item)
                }
            ) {
                Box(modifier = Modifier.wrapContentSize()) {

                    AsyncImage(
                        model = item.heroImageResId,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                        contentScale = ContentScale.Crop,
                        contentDescription = stringResource(id = R.string.hero_image),
                    )

                    item.heroNameResId?.let {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .align(alignment = Alignment.BottomStart)
                                .padding(all = Spaces.Items.heroNamePadding),
                            text = it,
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                            textAlign = if (layoutDirection == LayoutDirection.Ltr) TextAlign.Start else TextAlign.Right
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(width = Spaces.Items.snapRowItemSpace))
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun SnapListPreview() {
    MarvelTheme {
        Surface {
            SnapList(list = remember {
                mutableListOf()
            })
        }
    }
}