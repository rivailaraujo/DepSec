# DepSec 🔒
### Serviço de Apoio à Análise de Vulnerabilidades em Pacotes NPM ###

O **DepSec** é um serviço backend desenvolvido em **Micronaut + Groovy**, com o objetivo de **analisar vulnerabilidades de segurança em pacotes NPM**.  
Ele integra fontes públicas de dados de segurança, como o **Sonatype OSS Index** e o **GitHub Security Advisories**, consolidando informações relevantes em um formato unificado e de fácil consulta.
Este serviço backend trabalha ao lado da [DepSec Extension](https://github.com/rivailaraujo/DepSec-extension)
para exibição dos resultados.
---

## 🚀 Funcionalidades

- 🔍 Consulta automática de vulnerabilidades de pacotes NPM
- ⚙️ Integração com **Sonatype OSS Index**
- 🧩 Integração com **GitHub Security Advisories (GraphQL)**
- 🧠 Combinação inteligente dos resultados, evitando duplicações
- 🌐 API REST simples e documentada
- 🛡️ Autenticação segura via variáveis de ambiente

---

## 🏗️ Arquitetura

O DepSec é composto por um **serviço principal** (Micronaut) responsável por:
1. Receber o nome e versão de um pacote NPM;
2. Consultar vulnerabilidades conhecidas nas fontes externas;
3. Unificar os resultados;
4. Retornar uma resposta JSON com detalhes das falhas encontradas.

## ⚙️ Extrutura do Projeto
```
src/
├── main/
│   ├── groovy/com/rivail/
│   │   ├── controllers/
│   │   │   └── VulnerabilityController.groovy
│   │   ├── services/
│   │   │   └── VulnerabilityScannerService.groovy
│   │   └── models/
│   │       └── Vulnerability.groovy
│   └── resources/application.yml
└── test/
└── groovy/com/rivail/
└── VulnerabilityServiceSpec.groovy
```

## ⚙️ Tecnologias Utilizadas

| Camada | Tecnologia |
|--------|-------------|
| Linguagem | Groovy |
| Framework | Micronaut |
| Build Tool | Gradle |
| Cliente HTTP | Micronaut HTTP Client |
| Integrações | GitHub GraphQL API, Sonatype OSS Index |
| Formato de resposta | JSON |

---

## 💡 Endpoints Principal

### `GET /?packageName=<nome>&version=<versão>`

**Exemplo de uso:**

**Retorno:**
```json
{
    "packageName": "express@4.18.2",
    "vulnerabilities": [
        {
            "id": "CVE-2024-10491",
            "displayName": "CVE-2024-10491",
            "title": "[CVE-2024-10491] CWE-74: Improper Neutralization of Special Elements in Output Used by a Downstream Component ('Injection')",
            "description": "A vulnerability has been identified in the Express response.links function, allowing for arbitrary resource injection in the Link header when unsanitized data is used.\n\nThe issue arises from improper sanitization in `Link` header values, which can allow a combination of characters like `,`, `;`, and `<>` to preload malicious resources.\n\nThis vulnerability is especially relevant for dynamic parameters.\n\nSonatype's research suggests that this CVE's details differ from those defined at NVD. See https://ossindex.sonatype.org/vulnerability/CVE-2024-10491 for details",
            "cvssScore": 5.3,
            "cvssVector": "CVSS:3.1/AV:N/AC:L/PR:N/UI:N/S:U/C:N/I:L/A:N",
            "cwe": "CWE-74",
            "cve": "CVE-2024-10491",
            "reference": "https://ossindex.sonatype.org/vulnerability/CVE-2024-10491?component-type=npm&component-name=express",
            "externalReferences": [
                "http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-10491",
                "https://www.herodevs.com/vulnerability-directory/cve-2024-10491"
            ]
        }, ....
    ]
}
```

**Configurações de .env:**

```yml
GITHUB_TOKEN=ghp_xxxxxxxxxxxxxxxxxxxxxxx
SONATYPE_USERNAME=seu_usuario
SONATYPE_TOKEN=seu_token
```

---