import { MessageDTO } from './message.dto';

export interface ResponseDTO {
    data: any;
    messages: Array<MessageDTO>;
}