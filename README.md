# poc-multi-tenant

This is a POC for Multi-tenant using MultiTenantSpringLiquibase.

It reads from a View called schema_view in the PostgreSQL public schema and creates schemas based on the values present on the view.
Then it executes Liquibase for every schema created using MultiTenantSpringLiquibase.

The changelog used for the created schemas is located at ``db/changelog/schemas/db.changelog-specific.yaml``

When the application starts, it executes the MultiTenantSpringLiquibase, so if the values in the view change, new schemas will be created.

Using this, the public schema is executed before all and may have different tables from the others.
