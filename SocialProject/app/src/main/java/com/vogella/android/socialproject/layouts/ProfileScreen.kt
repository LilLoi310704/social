package com.example.social.layouts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.vogella.android.socialproject.R
import com.example.social.db.userPostDataProvider
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun ProfileScreen(navController: NavController){
    var isPressed by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize(), // Đảm bảo Column chiếm toàn bộ kích thước có sẵn
        verticalArrangement = Arrangement.SpaceBetween // Căn đều giữa các thành phần
    ) {
        LazyColumn() {
            item {
                Firstline5(navController)
            }
            item {
                Spacer(Modifier.height(15.dp))
                FriendLine(navController)
            }
        }
        Spacer(Modifier.height(185.dp))
        Divider(
            color = colorResource(R.color.pink),
            thickness = 1.dp
        )
        // Box ở dưới cùng
        Box(
            modifier = Modifier
                .fillMaxWidth() // Chiếm toàn bộ chiều rộng
                .height(52.dp) // Thiết lập chiều cao cho Box mới
                .background(color = Color.White), // Bạn có thể đổi màu theo ý thích
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 2.dp, bottom = 2.dp)){
                Button(
                    onClick = { isPressed = !isPressed },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                )
                {
                    Row(){
                    Text(text="Shin Văn Nô",color= colorResource(R.color.pink), fontSize = 20.sp)
                        Spacer(Modifier.width(10.dp))
                        if (isPressed) {
                            Image(
                                painter = painterResource(id = R.drawable.uparrow), // Thay đổi thành icon khi bấm
                                contentDescription = "Icon Pressed",
                                modifier = Modifier.size(20.dp) // Kích thước của icon
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.down), // Icon mặc định
                                contentDescription = "Default Icon",
                                modifier = Modifier.size(20.dp) // Kích thước của icon
                            )
                        }
                    }
                }
                Spacer(Modifier.width(95.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                )
                {
                    Image(
                        painter = painterResource(R.drawable.menubar),
                        contentDescription = "option Icon",
                        modifier = Modifier
                            .size(29.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Firstline5(navController: NavController){
    Column(modifier= Modifier.fillMaxWidth()){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(195.dp)) {
            GetNenHinhDaiDien()
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
                .align(Alignment.BottomStart)
                .offset(y = 1.dp)) {
                Row( verticalAlignment = Alignment.CenterVertically) {
                    GetHinhDaiDienProfile()
                    Spacer(Modifier.width(15.dp))
                    Box(modifier=Modifier.offset(y=25.dp)){
                        Row() {
                            Column {
                                Text(text = "0",modifier=Modifier.padding(start = 25.dp))
                                Text(text = "Bài viết")
                            }
                            Spacer(Modifier.width(20.dp))
                            Column {
                                Text(text = "0",modifier=Modifier.padding(start = 25.dp))
                                Text(text = "Bạn bè")
                            }
                            Spacer(Modifier.width(20.dp))
                            Column {
                                Text(text = "0",modifier=Modifier.padding(start = 25.dp))
                                Text(text = "Theo dõi")
                            }
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()){
            Spacer(Modifier.height(10.dp))
            Text(text=Firebase.auth.currentUser?.displayName.toString(),modifier=Modifier.padding(start = 15 .dp),
                fontWeight = FontWeight.ExtraBold, fontSize = 20.sp
            )
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {navController.navigate("profileEdit")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    // Đặt kích thước cho nút
                    .border(
                        BorderStroke(1.dp, color = colorResource(R.color.pink)),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .size(width = 200.dp, height = 32.dp)
            ) {
                // Sử dụng Box để căn giữa nội dung
                Box(
                    // Chiếm toàn bộ không gian của nút
                    contentAlignment = Alignment.Center // Căn giữa nội dung
                ) {
                    Text(
                        text = "Chỉnh sửa trang cá nhân",
                        color = colorResource(R.color.pink),
                        fontSize = 12.sp,
                        modifier= Modifier
                            .padding(start = 11.dp)
                            .offset(y = -1.dp)
                        )
                    }
            }
        }
    }
}
@Composable
fun FriendLine(navController: NavController){
    Column(){
        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)){
            Text(text="Bạn bè", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp,
                color=Color.Gray,
                modifier = Modifier.alpha(0.5f)
            )
            Spacer(Modifier.width(155.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .width(165.dp)
                    .offset(y = -4.dp)
                    .height(30.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), // Chiếm toàn bộ không gian của Button
                    contentAlignment = Alignment.CenterEnd // Căn trái nội dung
                ) {
                    Text(
                        text = "Tìm bạn bè",
                        color = colorResource(R.color.pinkBlur),
                        fontSize = 12.sp,
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
        ) {
            // Chia danh sách thành các hàng 3 phần tử
            userPostDataProvider.friendList.chunked(3).forEach { friendRow ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    friendRow.forEach { friend ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các phần tử trong cột
                        ) {
                            GetHinhDaiDienProfileFriend(friend.avtFriend.avatarRes)
                            Text(text = friend.nameFriend)
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(
                onClick = {navController.navigate("allFreind")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.white)
                ),
                modifier = Modifier
                    // Đặt kích thước cho nút
                    .border(
                        BorderStroke(1.dp, color = colorResource(R.color.pink)),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(width = 335.dp, height = 32.dp)

            ) {
                Text(text = "Xem tất cả", color = colorResource(R.color.pink))
            }
        }
    }
}
@Composable
fun GetNenHinhDaiDien(){
    // Ảnh chính
    var backgroundAvatar by remember { mutableStateOf("") }
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("users").document(Firebase.auth.currentUser!!.uid)
    docRef.get().addOnSuccessListener { documentSnapshot: DocumentSnapshot ->
        if (documentSnapshot.exists()) {
            backgroundAvatar = documentSnapshot.getString("backgroundAvatar").toString()
        }
    }
    Image(
        painter = rememberAsyncImagePainter(model = backgroundAvatar),
        contentDescription = "Main Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(5.dp, colorResource(R.color.pinkBlur)) // Viền ảnh chính
    )
}
@Composable
fun GetHinhDaiDienProfile(){
    val avatar = Firebase.auth.currentUser?.photoUrl
    Image(
        painter = rememberAsyncImagePainter(model = avatar),
        contentDescription = "Circular Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(90.dp) // Kích thước ảnh tròn
            .clip(CircleShape) // Cắt ảnh thành hình tròn
            .border(5.dp, colorResource(R.color.pinkBlur), CircleShape)
    )
}
@Composable
fun GetHinhDaiDienProfileFriend(img2 : Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(8.dp)) // Thêm spacer ở đây để đẩy hình ảnh xuống
        Image(
            painter = painterResource(img2),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 92.dp, height = 51.dp)
                .clip(RoundedCornerShape(2.dp))
        )
    }
}