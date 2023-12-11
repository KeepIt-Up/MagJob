FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="magjob-backend-auth"
LABEL org.opencontainers.image.authors="Michał (Deryl) Konieczny, Szymon (stanczjo) Małecki, Sebastian (WallyS) Kutny"
LABEL org.opencontainers.image.source="https://github.com/KeepIt-Up/MagJob"
LABEL org.opencontainers.image.url="https://github.com/KeepIt-Up/MagJob"
LABEL org.opencontainers.image.vendor="KeepIt-Up"
LABEL org.opencontainers.image.version="0.0.1"
LABEL org.opencontainers.image.description="MagJob backend authentication"
#LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1"

ENV SERVER_PORT=8081

EXPOSE 8081

COPY target/magjob-backend-auth-${VERSION}.jar /opt/magjob-backend-auth/magjob-backend-auth.jar

CMD ["java", "-jar", "/opt/magjob-backend-auth/magjob-backend-auth.jar"]
