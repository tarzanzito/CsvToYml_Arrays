Objective:
 convert csv file into yml file. Create yml List <object> or map <(string, object) content.
 
 Note:
 All methods are static because I do not understand anything about OOP !!!! :D ;P
 (So, simple and meets the objectives)

Example
--------

CSV FILE (flat input)
---------------------
Identificação Civil;http://interop.gov.pt/MDC/Cidadao/NIC;Cartão de Cidadão
Nome Próprio;http://interop.gov.pt/MDC/Cidadao/NomeProprio;Cartão de Cidadão
Apelido;http://interop.gov.pt/MDC/Cidadao/NomeApelido;Cartão de Cidadão
Data de Nascimento;http://interop.gov.pt/MDC/Cidadao/DataNascimento;Cartão de Cidadão


YML (output as List)
--------------------
attributes:
  attribute-data-list:
    -
      name: Identificação Civil
      attr: http://interop.gov.pt/MDC/Cidadao/NIC
      group: Cartão de Cidadão
    -
      name: Nome Próprio
      attr: http://interop.gov.pt/MDC/Cidadao/NomeProprio
      group: Cartão de Cidadão
    -
      name: Apelido
      attr: http://interop.gov.pt/MDC/Cidadao/NomeApelido
      group: Cartão de Cidadão
      
YML (output as Map)  (key is compose by last two nodes of 'attr' variable)
-------------------
attributes:
  attribute-data-map:
    Cidadao-NIC:
      name: Identificação Civil
      attr: http://interop.gov.pt/MDC/Cidadao/NIC
      group: Cartão de Cidadão

    Cidadao-NomeProprio:
      name: Nome Próprio
      attr: http://interop.gov.pt/MDC/Cidadao/NomeProprio
      group: Cartão de Cidadão

    Cidadao-NomeApelido:
      name: Apelido
      attr: http://interop.gov.pt/MDC/Cidadao/NomeApelido
      group: Cartão de Cidadão
      
      
