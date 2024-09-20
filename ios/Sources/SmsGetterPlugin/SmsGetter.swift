import Foundation

@objc public class SmsGetter: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
