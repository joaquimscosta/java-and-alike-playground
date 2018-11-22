# Java/JVM programming playground

## Security
### SSL/TLS

- https://www.symantec.com/content/en/uk/enterprise/white_papers/b-beginners-guide-to-ssl-certificates_WP.pdf
- http://www.steves-internet-guide.com/ssl-certificates-explained
- https://hpbn.co/transport-layer-security-tls

**SSL** - Secure Socket Layer
**TLS** - Transport Layer Security

SSL and TLS are used interchangeably, but note that SSL (old version) is now deprecated, because it is [not secure](https://en.wikipedia.org/wiki/POODLE). Most of the times when people are talking about SSL they mean TLS (basically the new version of SSL).

[OpenSSL](https://www.openssl.org/) is the *de facto* TLS implementation.
You can use OpenSSL to generate you own TLS certificate, but *self-signed certificate*, but it is best to get the certificate from a central authority to.
**Self-Signed Certificate** a self-signed certificate is an SSL certificate that is not signed by a trusted, central authority in the SSL/TLS certificate ecosystem.
![TLS](https://assets.publishing.service.gov.uk/government/uploads/system/uploads/image_data/file/56800/TLS_diagram.png)


Central authorities that provide TLS certificate 
- [Let's Encrypt](https://letsencrypt.org/)
- [Cloudflare](https://www.cloudflare.com)

[How To Secure Nginx with Let's Encrypt on Ubuntu 16.04](https://www.digitalocean.com/community/tutorials/how-to-secure-nginx-with-let-s-encrypt-on-ubuntu-16-04)

## Authentication
**Authentication** is the process to identify with whom the server is communicating.
Some methods used to identify the user/entity
- [HTTP Basic Auth](https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication) (Username/Password)
- [OAuth](https://oauth.net/1/) and [Auth 2.0](https://oauth.net/2/)
- [Single Sign On](https://en.wikipedia.org/wiki/Single_sign-on)
- [JSON Web Tokens (JWT)](https://jwt.io/)

## Authorization
**Authorization** is about managing permission for users.


