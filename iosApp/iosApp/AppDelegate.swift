import Shared
import UIKit

class AppDelegate : NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {
        initLogger()
        registerNotifications()
        return true
    }
    
    private func initLogger() {
        LoggerKt.doInitLogger()
    }
    
    private func registerNotifications() {
        let notificationRegister = NotificationRegister()
        notificationRegister.register()
    }
}
