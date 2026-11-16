package com.example.shopease.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class SampleProduct(
    val name: String,
    val category: String,
    val price: String,
    val label: String,
    val background: Color
)

private val sampleProducts = listOf(
    SampleProduct(
        name = "Wireless Headphones",
        category = "Electronics",
        price = "£29.99",
        label = "WH",
        background = Color(0xFFEDE9FE)
    ),
    SampleProduct(
        name = "Everyday Backpack",
        category = "Fashion",
        price = "£24.99",
        label = "BP",
        background = Color(0xFFFCE7F3)
    ),
    SampleProduct(
        name = "Ceramic Mug",
        category = "Home",
        price = "£8.99",
        label = "CM",
        background = Color(0xFFDBEAFE)
    ),
    SampleProduct(
        name = "Smart Watch",
        category = "Electronics",
        price = "£49.99",
        label = "SW",
        background = Color(0xFFE0E7FF)
    )
)

@Composable
fun DashboardScreen() {
    var selectedCategory by rememberSaveable {
        mutableStateOf("All")
    }

    val categories = listOf(
        "All",
        "Electronics",
        "Fashion",
        "Home"
    )

    val visibleProducts = sampleProducts.filter { product ->
        selectedCategory == "All" ||
                product.category == selectedCategory
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF172554))
            .safeDrawingPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F7FC)),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                DashboardHeader()
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Shop by category",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172554)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(categories) { category ->
                            FilterChip(
                                selected = selectedCategory == category,
                                onClick = {
                                    selectedCategory = category
                                },
                                label = {
                                    Text(text = category)
                                }
                            )
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEDE9FE)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Find your everyday favourites",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4C1D95)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Explore tech, fashion and home essentials.",
                            fontSize = 14.sp,
                            color = Color(0xFF5B5270)
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (selectedCategory == "All") {
                            "Featured products"
                        } else {
                            selectedCategory
                        },
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172554)
                    )

                    Text(
                        text = "${visibleProducts.size} items",
                        fontSize = 13.sp,
                        color = Color(0xFF6B7280)
                    )
                }
            }

            items(
                items = visibleProducts,
                key = { product -> product.name }
            ) { product ->
                ProductCard(product = product)
            }

            item {
                Text(
                    text = "Sample products for the ShopEase prototype",
                    modifier = Modifier.padding(horizontal = 20.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF6B7280)
                )
            }
        }
    }
}

@Composable
private fun DashboardHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF172554),
                        Color(0xFF4338CA),
                        Color(0xFF9333EA),
                        Color(0xFFDB2777)
                    )
                ),
                shape = RoundedCornerShape(
                    bottomStart = 28.dp,
                    bottomEnd = 28.dp
                )
            )
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Text(
            text = "ShopEase",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Discover more. Shop with ease.",
            fontSize = 15.sp,
            color = Color.White.copy(alpha = 0.9f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "A little something\nfor every day.",
            fontSize = 26.sp,
            lineHeight = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun ProductCard(product: SampleProduct) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .background(
                        color = product.background,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.label,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4C1D95)
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.category,
                    fontSize = 12.sp,
                    color = Color(0xFF6B7280)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF172554)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = product.price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7C3AED)
                )
            }
        }
    }
}