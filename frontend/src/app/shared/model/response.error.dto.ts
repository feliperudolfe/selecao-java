import { MessageDTO } from './message.dto';

export interface ResponseErroDTO {
    error: {
        messages: Array<MessageDTO>;
    };
}