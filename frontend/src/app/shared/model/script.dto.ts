import { ResultDTO } from './result.dto';

export interface ScriptDTO {
  testClass: string;
  testId: string;
  testName: string;
  testDescription: string;
  csvPath: string;
  hasSuccess?: boolean;
  hasFailure?: boolean;
  hasError?: boolean;
  hasPending?: boolean;
  sqlPath: Array<string>;
  results: Array<ResultDTO>;
}