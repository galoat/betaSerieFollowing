FROM ubuntu:14.04

# Install Java7
RUN apt-get update && \
    apt-get install -y software-properties-common && \
    add-apt-repository ppa:webupd8team/java && apt-get update && \
    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean 

# Install Deps
RUN dpkg --add-architecture i386 && \
    apt-get update && \
    apt-get install -y --force-yes expect git wget libc6-i386 lib32stdc++6 lib32gcc1 lib32ncurses5 lib32z1 python curl && \
    apt-get clean

# Install Android SDK
ENV ANDROID_SDK_VERSION=r24.4.1 \
    ANDROID_BUILD_TOOLS_VERSION=23.0.3 \
    ANDROID_API_LEVELS=android-23

RUN cd /opt && \
    wget --output-document=android-sdk.tgz --quiet http://dl.google.com/android/android-sdk_${ANDROID_SDK_VERSION}-linux.tgz && \
    tar xzf android-sdk.tgz && \
    rm -f android-sdk.tgz && \
    chown -R root.root android-sdk-linux

ENV ANDROID_HOME /opt/android-sdk-linux
ENV PATH ${PATH}:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools

RUN mkdir -p "${ANDROID_HOME}/licenses" && \
    echo -e "\n8933bad161af4178b1185d1a37fbf41ea5269c55" >"${ANDROID_HOME}/licenses/android-sdk-license" && \
    echo -e "\n84831b9409646a918e30573bab4c9c91346d8abd" >"${ANDROID_HOME}/licenses/android-sdk-preview-license" && \
    echo -e "\nd975f751698a77b662f1254ddbeed3901e976f5a" >"${ANDROID_HOME}/licenses/intel-android-extra-license"

RUN echo y | android update sdk --no-ui -a --filter tools,platform-tools,${ANDROID_API_LEVELS},build-tools-${ANDROID_BUILD_TOOLS_VERSION},extra-android-support,extra-android-m2repository,extra-google-m2repository
