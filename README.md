AnUpNav
=======

Up navigation not receiving result from children activity

A is the parent Activity (corresponding fragment: AFragment)
B is the child Activity (corresponding fragment: BFragment)

AFragment calls startActivityForResult and starts B

BFragment should accept a simple Double, make B set that double as a result and when Up navigation is called,
return to A, showing in a textView the Double introduced in B.

The project is using pfn's android-sdk-plugin and scala.
AFragment has a "DEBUG\_TAG" so that it some debugging with ADB Logcat can be done.

I can see the "Activity.RESULT\_CANCELLED" while debugging (if I press the back arrow instead of the up navigation key in the ActionBar), however, I cannot receive "Activity.RESULT\_OK".

I need some help, please.

Thanks
