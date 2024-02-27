import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        LoggerKt.doInitLogger()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
