package com.nelson.example.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.UIKit.UIView
import platform.UIKit.UIViewAutoresizingFlexibleHeight
import platform.UIKit.UIViewAutoresizingFlexibleWidth
import platform.WebKit.WKWebView

//Este componente sera para Andoid y Ios
@Composable
actual fun VideoPlayer(modifier: Modifier, url: String) {
    val webView = remember { WKWebView() }

    UIKitView(
        modifier = modifier,
        factory = {
            val container = UIView().apply {
                autoresizingMask = UIViewAutoresizingFlexibleWidth or UIViewAutoresizingFlexibleHeight
            }

            webView.apply {
                autoresizingMask = UIViewAutoresizingFlexibleWidth or UIViewAutoresizingFlexibleHeight
                loadRequest(NSURLRequest(NSURL(string = url)))
            }

            container.addSubview(webView)
            container
        },
        update = { views ->
            //Si se pierde el Web View busca el original y me actualice la vista
            views.subviews().firstOrNull{ it is WKWebView}

        }
    )
}