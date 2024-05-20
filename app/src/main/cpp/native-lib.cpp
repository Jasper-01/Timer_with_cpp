#include <jni.h>
#include <string>
#include <chrono>
#include <thread>
#include <atomic>

bool running = false;
std::chrono::steady_clock::time_point start_time;
std::atomic<bool> reset_requested(false);

void resetTimer() {
    running = false;
    reset_requested = false;
    start_time = std::chrono::steady_clock::now();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_timerwcpp_MainActivity_startTimer(JNIEnv *env, jobject /* this */) {
    if (!running || reset_requested) {
        start_time = std::chrono::steady_clock::now();
        running = true;
        reset_requested = false;
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_timerwcpp_MainActivity_stopTimer(JNIEnv *env, jobject /* this */) {
    running = false;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_timerwcpp_MainActivity_resetTimer(JNIEnv *env, jobject /* this */) {
    reset_requested = true;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_timerwcpp_MainActivity_getElapsedTime(JNIEnv *env, jobject /* this */) {
    if (!running) {
        return env->NewStringUTF("00:00");
    }
    auto now = std::chrono::steady_clock::now();
    auto elapsed = std::chrono::duration_cast<std::chrono::seconds>(now - start_time).count();
    int minutes = elapsed / 60;
    int seconds = elapsed % 60;
    char buffer[6];
    snprintf(buffer, sizeof(buffer), "%02d:%02d", minutes, seconds);
    return env->NewStringUTF(buffer);
}
