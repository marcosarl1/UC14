function setCookies(cookieName, cookieValue, expirationDays) {
    let expiration = "";
    if (expirationDays) {
        const date = new Date();
        date.setTime(date.getTime() + (expirationDays * 24 * 60 * 60 * 1000)); // 24hrs 60min 60sec 1000millisecond
        expiration = "; expires=" + date.toUTCString();
    }
    document.cookie = `${encodeURIComponent(cookieName)}=${encodeURIComponent(cookieValue)}${expiration}; path=/`;
}

function getCookies(cookieName) {
    const cookieIdentifier  = `${encodeURIComponent(cookieName)}=`;
    const allCookies = document.cookie.split(';');
    for (const cookie of allCookies) {
        if (cookie.startsWith(cookieIdentifier)) {
            return decodeURIComponent(cookie.substring(cookieIdentifier.length))
        }
    }
    return null;
}