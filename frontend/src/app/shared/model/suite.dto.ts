import { ScriptDTO } from './script.dto';

export interface SuiteDTO {
  suiteClass: string;
  suiteId: string;
  suiteName: string;
  suiteDescription: string;
  hasSuccess?: boolean;
  hasFailure?: boolean;
  hasError?: boolean;
  hasPending?: boolean;
  suiteSqlPath: Array<string>;
  scripts: Array<ScriptDTO>;
}