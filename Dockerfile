FROM maven:3.9.6-eclipse-temurin-21

# Install required system libs
RUN apt-get update && \
    apt-get install -y wget curl unzip gnupg \
    libgtk-3-0 libdbus-glib-1-2 libasound2 \
    libx11-xcb1 libxt6 libxcomposite1 libxcursor1 \
    libxdamage1 libxrandr2 libgbm1 libnss3 \
    libatk1.0-0 libatk-bridge2.0-0 libdrm2 \
    libxss1 libxext6 xdg-utils && \
    apt-get clean

# Install Google Chrome
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-linux.gpg && \
    echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-linux.gpg] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    apt-get clean

# Install Firefox (REAL binary, NOT snap)
RUN wget -q https://download.mozilla.org/?product=firefox-latest&os=linux64 -O firefox.tar.bz2 && \
    tar -xjf firefox.tar.bz2 && \
    mv firefox /opt/firefox && \
    ln -s /opt/firefox/firefox /usr/bin/firefox && \
    rm firefox.tar.bz2

# Install Geckodriver
RUN wget -q https://github.com/mozilla/geckodriver/releases/latest/download/geckodriver-v0.35.0-linux64.tar.gz && \
    tar -xzf geckodriver-v0.35.0-linux64.tar.gz && \
    mv geckodriver /usr/local/bin/ && \
    rm geckodriver-v0.35.0-linux64.tar.gz