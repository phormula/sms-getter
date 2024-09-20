// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "SmsGetter",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "SmsGetter",
            targets: ["SmsGetterPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "SmsGetterPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/SmsGetterPlugin"),
        .testTarget(
            name: "SmsGetterPluginTests",
            dependencies: ["SmsGetterPlugin"],
            path: "ios/Tests/SmsGetterPluginTests")
    ]
)