import { Server } from "./server";

export interface CustomResponse {
    timeStamp: Date;
    statusCode: number;
    statsu: string;
    reason: string;
    message: string;
    developerMessage: string;
    data: { servers?: Server[], server?: Server };
}