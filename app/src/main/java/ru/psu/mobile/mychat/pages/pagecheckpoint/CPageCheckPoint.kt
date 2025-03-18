package ru.psu.mobile.mychat.pages.pagecheckpoint

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import ru.psu.mobile.mychat.model.CCheckPointWithRelations
import java.util.UUID

@Composable
fun CPageCheckPoint(
    id : String,
    modifier                                : Modifier = Modifier
) {
    val viewModel                           : CViewModelPageCheckPoint
                                            = viewModel()

    viewModel.setId(UUID.fromString(id))
    val checkPointWRState                   = viewModel.checkpoint.collectAsState()

    Column {
        Text("Это страница с информацией по контрольной точке: ${checkPointWRState.value.checkpoint.name}")
        Button(
            onClick = {
                viewModel.addPhoto()
            }
        ) {
            Text("Добавить фотографию")
        }
        CPhotoGrid(checkPointWRState)
    }
}
@Composable
fun CPhotoGrid(
    checkPointWRState: State<CCheckPointWithRelations>
)
{
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        content = {
            items(checkPointWRState.value.photos.size) { index ->
                Card(modifier = Modifier.height(300.dp)) {
                    checkPointWRState.value.photos[index].bitmap?.let{ bitmap ->
//                        Image(
//                            bitmap = bitmap.asImageBitmap(),
//                            contentDescription = "Photo",
//                            contentScale = ContentScale.FillBounds
//                        )
                        AsyncImage(
                            model = "http://192.168.1.4:50880/files/61478ed0-2c71-4f11-b90c-d0431cc7c82b",
                            contentDescription = null,
                        )

                    }?:run{
                        Text("Проблема при загрузке изображения")
                    }

                }
            }
        }
    )
}