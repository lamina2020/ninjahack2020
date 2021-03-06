/**
 * Fight API
 * This API allows a hero and a villain to fight
 *
 * OpenAPI spec version: 1.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

/**
 * The hero fighting against the villain
 */
export class Credito {
  constructor(
    public idusuario?: bigint,
    public iban?: string,
    public importe?: string,
    public interes?: string,
    public plazo?: string,
    public tipo?: string
) {
}
}
